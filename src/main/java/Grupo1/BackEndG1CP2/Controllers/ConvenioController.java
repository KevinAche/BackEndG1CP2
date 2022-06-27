package Grupo1.BackEndG1CP2.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import Grupo1.BackEndG1CP2.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Grupo1.BackEndG1CP2.Models.Convenio;
import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import Grupo1.BackEndG1CP2.Models.ResponsablePPP;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ConveniosRepository;
import Grupo1.BackEndG1CP2.Repositories.DocenteRepository;
import Grupo1.BackEndG1CP2.Repositories.EmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.ResponsablePPPRepository;


@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionConvenios")
public class ConvenioController {

	@Autowired
	private ConveniosRepository conveniosRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private PersonalEmpresaRepository personalEmpresaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private DocenteRepository docenteRepository;

	@Autowired
	private ResponsablePPPRepository responsablePPPRepository;

	@GetMapping("/ListarConvenios")
	public ResponseEntity<RespuestaGenerica> ListaConvenios() {
		List<Convenio> data = new ArrayList<>();
		RespuestaGenerica<Convenio> respuesta = new RespuestaGenerica<>();
		try {
			data = conveniosRepository.findAll();
			respuesta.setMensaje("Se genero LISTADO CONVENIOS EXITOXAMENTE");
			respuesta.setData(data);
			respuesta.setEstado(0);
		} catch (Exception e) {
			respuesta.setMensaje("Hubo un problema al generar LISTADO CONVENIOS, causa ->" + e.getCause()
					+ " || messagge->" + e.getMessage());
			respuesta.setData(data);
			respuesta.setEstado(1);
		}
		return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
	}

	@PostMapping("/CrearConvenio/{cedula_gerente}/{cedula_responsable}")
	public ResponseEntity<RespuestaGenerica> CrearConvenio(@RequestBody Convenio convenioEnviado,
			@PathVariable String cedula_gerente, @PathVariable String cedula_responsable) {
		List<Convenio> data = new ArrayList<>();
		RespuestaGenerica<Convenio> respuesta = new RespuestaGenerica<>();
		HttpStatus estado = HttpStatus.CREATED;
		try {
			Persona persona = personaRepository.findByCedula(cedula_gerente);
			Persona responsabble = personaRepository.findByCedula(cedula_responsable);

			PersonalEmpresa gerente = personalEmpresaRepository.findByPersona(persona);
			if (gerente != null && responsabble != null) {
				Docente docente = docenteRepository.findByPersona(responsabble);
				ResponsablePPP responsablePPP = responsablePPPRepository.findByDocente(docente);
				convenioEnviado.setResponsablePPP(responsablePPP);
				convenioEnviado.setGerente(gerente);
				Convenio convenioguardar = conveniosRepository.save(convenioEnviado);
				if (convenioguardar != null) {
					data.add(convenioguardar);
					respuesta.setMensaje("SE REGISTRO CONVENIO CORRECTAMENTE");
					respuesta.setData(data);
					respuesta.setEstado(0);
				} else {
					respuesta.setMensaje(
							"NO SE REGISTRO CONVENIO CORRECTAMENTE debido a que el id empresa ingresado no fue encontrado");
					respuesta.setData(data);
					respuesta.setEstado(1);
					estado = HttpStatus.BAD_REQUEST;
				}
			} else {
				respuesta.setMensaje(
						"NO SE REGISTRO CONVENIO CORRECTAMENTE debido a que la cedula ingresada fue encontrada");
				respuesta.setData(data);
				respuesta.setEstado(1);
				estado = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			respuesta.setMensaje("Hubo un problema al insertar CONVENIO, causa ->" + e.getCause() + " || message -> "
					+ e.getMessage());
			respuesta.setData(data);
			respuesta.setEstado(1);
			estado = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
	}

	@PutMapping("/EditarConvenio/{id}")
	public ResponseEntity<RespuestaGenerica> EditarConvenio(@RequestBody Convenio convenioEnviado, @PathVariable Long id){
		List<Convenio> data = new ArrayList<>();
		RespuestaGenerica<Convenio> respuesta = new RespuestaGenerica<>();
		AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
		try {
			Convenio con = conveniosRepository.findById(id)
					.map(res ->{
						res.setDocumento(convenioEnviado.getDocumento());

						//EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
						data.add(res);
						respuesta.setMensaje("SE MODIFICO Convenio CORRECTAMENTE");
						respuesta.setData(data);
						respuesta.setEstado(0);
						//SE RETORNA Actividades MODIFICADA
						return conveniosRepository.save(res);
					})
					.orElseGet(()->{
						respuesta.setMensaje("NO SE ENCONTRO Convenio CON EL ID INGRESADO: "+id);
						respuesta.setData(data);
						respuesta.setEstado(1);
						estado.set(HttpStatus.BAD_REQUEST);
						return new Convenio();
					});
		}catch (Exception e){
			respuesta.setMensaje("Hubo un problema al MODIFICAR Convenio, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
			respuesta.setData(data);
			respuesta.setEstado(1);
			estado.set(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
	}

	@DeleteMapping("/EliminarActividades/{id}")
	public ResponseEntity EliminarConvenio (@PathVariable Long id ){
		List<Convenio> data = new ArrayList<Convenio>();
		RespuestaGenerica<Convenio> respuesta = new RespuestaGenerica<>();
		HttpStatus estado  = HttpStatus.OK;

		try {

			conveniosRepository.deleteById(id);
			if(conveniosRepository!=null){
				data.add(new Convenio());
				respuesta.setMensaje("SE ELIMINO Convenio CORRECTAMENTE");
				respuesta.setData(data);
				respuesta.setEstado(0);
			}else{
				estado= HttpStatus.BAD_REQUEST;
				data.add(null);
				respuesta.setMensaje("NO SE ELIMINO Convenio CORRECTAMENTE");
				respuesta.setData(data);
				respuesta.setEstado(1);
			}
		} catch (Exception e) {
			estado= HttpStatus.BAD_REQUEST;
			respuesta.setMensaje("Hubo un problema al ELIMINAR Convenio, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
			respuesta.setData(data);
			respuesta.setEstado(1);
		}

		return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
	}
}
