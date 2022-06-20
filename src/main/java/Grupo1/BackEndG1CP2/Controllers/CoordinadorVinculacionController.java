package Grupo1.BackEndG1CP2.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Grupo1.BackEndG1CP2.Models.CoordinadorVinculacion;
import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.CoordinadorVinculacionRepository;
import Grupo1.BackEndG1CP2.Repositories.DocenteRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionCoordinador")
public class CoordinadorVinculacionController {

	@Autowired
	private CoordinadorVinculacionRepository vinculacionRepository;

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private DocenteRepository docenteRepository;

	@GetMapping("/ListaCoordinadores")
	public ResponseEntity<RespuestaGenerica> ListarCoordinadores() {
		List<CoordinadorVinculacion> data = new ArrayList<>();
		RespuestaGenerica<CoordinadorVinculacion> respuesta = new RespuestaGenerica<>();
		try {
			data = vinculacionRepository.findAll();
			respuesta.setMensaje("Se genero LISTADO DE COORDINADORES");
			respuesta.setData(data);
			respuesta.setEstado(0);
		} catch (Exception e) {
			respuesta.setMensaje("Hubo un problema al generar LISTADO DE COORDINADORES, causa ->" + e.getCause()
					+ " || messagge->" + e.getMessage());
			respuesta.setData(data);
			respuesta.setEstado(1);
		}
		return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
	}

	@PostMapping("/CrearCoordinador/{cedula_docente}")
	public ResponseEntity<RespuestaGenerica> CrearCoordinador(@RequestBody CoordinadorVinculacion vinculacionEnviado,
			@PathVariable String cedula_docente) {
		List<CoordinadorVinculacion> data = new ArrayList<>();
		RespuestaGenerica<CoordinadorVinculacion> respuesta = new RespuestaGenerica<>();
		HttpStatus estado = HttpStatus.CREATED;
		try {
			Persona persona = personaRepository.findByCedula(cedula_docente);
			Docente docente = docenteRepository.findByPersona(persona);
			if (docente != null) {
				vinculacionEnviado.setDocente(docente);
				CoordinadorVinculacion vinculacionGuardar = vinculacionRepository.save(vinculacionEnviado);
				data.add(vinculacionGuardar);
				respuesta.setMensaje("SE REGISTRO COORDINADOR CORRECTAMENTE");
				respuesta.setData(data);
				respuesta.setEstado(0);

			} else {
				respuesta.setMensaje(
						"NO SE REGISTRO COORDINADOR CORRECTAMENTE debido a que la cedula ingresada no fue encontrada");
				respuesta.setData(data);
				respuesta.setEstado(1);
				estado = HttpStatus.BAD_REQUEST;
			}
		} catch (

		Exception e) {
			respuesta.setMensaje("Hubo un problema al insertar COORDINADOR, causa ->" + e.getCause() + " || message -> "
					+ e.getMessage());
			respuesta.setData(data);
			respuesta.setEstado(1);
			estado = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
	}

}
