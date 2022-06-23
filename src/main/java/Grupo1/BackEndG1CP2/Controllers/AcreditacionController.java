package Grupo1.BackEndG1CP2.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Grupo1.BackEndG1CP2.Models.Acreditacion;
import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Convenio;
import Grupo1.BackEndG1CP2.Models.CoordinadorVinculacion;
import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import Grupo1.BackEndG1CP2.Models.ResponsablePPP;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.AcreditacionRepository;
import Grupo1.BackEndG1CP2.Repositories.AlumnoRepository;
import Grupo1.BackEndG1CP2.Repositories.CoordinadorVinculacionRepository;
import Grupo1.BackEndG1CP2.Repositories.DocenteRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionAcreditacion")
public class AcreditacionController {
	
	@Autowired
	private AcreditacionRepository acreditacionRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private CoordinadorVinculacionRepository vinculacionRepository;
	
	@GetMapping("/ListaInformesAcreditados")
    public ResponseEntity<RespuestaGenerica> ListarActividades(){
        List<Acreditacion> data = new ArrayList<>();
        RespuestaGenerica<Acreditacion> respuesta = new RespuestaGenerica<>();
        try {
            data=acreditacionRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO DE ACREDITACIONES");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO DE ACREDITACIONES, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }
	
	@PostMapping("/CrearAcreditacion/{cedula_coordinador}/{cedula_alumno}")
	public ResponseEntity<RespuestaGenerica> CrearConvenio(@RequestBody Acreditacion acreditacionEnviado,
			@PathVariable String cedula_coordinador, @PathVariable String cedula_alumno) {
		List<Acreditacion> data = new ArrayList<>();
		RespuestaGenerica<Acreditacion> respuesta = new RespuestaGenerica<>();
		HttpStatus estado = HttpStatus.CREATED;
		try {
			Persona persona = personaRepository.findByCedula(cedula_alumno);
			Persona docente = personaRepository.findByCedula(cedula_coordinador);
			if (persona != null && docente != null) {
				Docente docente1 = docenteRepository.findByPersona(docente);
				Alumno alumno = alumnoRepository.findByPersona(persona);
				if (docente1 != null && alumno != null) {
					acreditacionEnviado.setAlumno(alumno);
					CoordinadorVinculacion vinculacion = vinculacionRepository.findByDocente(docente1);
					acreditacionEnviado.setVinculacion(vinculacion);
					Acreditacion acreditacion = acreditacionRepository.save(acreditacionEnviado);
					if(acreditacion != null) {
						data.add(acreditacion);
						respuesta.setMensaje("SE REGISTRO ACREDITACION CORRECTAMENTE");
						respuesta.setData(data);
						respuesta.setEstado(0);
					}else {
						respuesta.setMensaje(
								"NO SE REGISTRO ACREDITACION CORRECTAMENTE debido a que la CEDULA del COORDINADOR ingresado no fue encontrado");
						respuesta.setData(data);
						respuesta.setEstado(1);
						estado = HttpStatus.BAD_REQUEST;
					}
				} else {
					respuesta.setMensaje(
							"NO SE REGISTRO ACREDITACION CORRECTAMENTE debido a que DOCENTE/ALUMNO ingresado no fue encontrado");
					respuesta.setData(data);
					respuesta.setEstado(1);
					estado = HttpStatus.BAD_REQUEST;
				}
			} else {
				respuesta.setMensaje(
						"NO SE REGISTRO ACREDITACION CORRECTAMENTE debido a que la cedula ingresada no fue encontrada");
				respuesta.setData(data);
				respuesta.setEstado(1);
				estado = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			respuesta.setMensaje("Hubo un problema al insertar ACREDITACION, causa ->" + e.getCause() + " || message -> "
					+ e.getMessage());
			respuesta.setData(data);
			respuesta.setEstado(1);
			estado = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
	}

}
