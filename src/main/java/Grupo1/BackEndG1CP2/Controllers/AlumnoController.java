package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Carrera;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarAlumnos;
import Grupo1.BackEndG1CP2.Repositories.AlumnoRepository;
import Grupo1.BackEndG1CP2.Repositories.CarreraRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarAlumnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GestionAlumno")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ListarAlumnosRepository listaAlumnosRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private CarreraRepository carreraRepository;



    @GetMapping("/ListaAlumnos")
    public ResponseEntity<RespuestaGenerica> ListarAlumnos(){
       List<VistaListarAlumnos> data = new ArrayList<>();
       RespuestaGenerica<VistaListarAlumnos> respuesta = new RespuestaGenerica<>();
       try{
           data= listaAlumnosRepository.findAll();
           respuesta.setMensaje("Se genero LISTADO ALUMNOS EXITOXAMENTE");
           respuesta.setData(data);
           respuesta.setEstado(0);
       }catch (Exception e){
           respuesta.setMensaje("Hubo un problema al generar LISTADO ALUMNOS, causa ->"+e.getCause()+" || message->"+e.getMessage());
           respuesta.setData(data);
           respuesta.setEstado(1);
       }
       return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearAlumno/{id_persona}/{id_carrera}")
    public ResponseEntity<RespuestaGenerica> CrearAlumno(@RequestBody Alumno alumnoEnviado,@PathVariable Long id_persona,@PathVariable Long id_carrera){
        List<Alumno> data = new ArrayList<>();
        RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Optional<Persona> persona = personaRepository.findById(id_persona);
            if(!persona.isEmpty()){
                Optional<Carrera> carrera = carreraRepository.findById(id_carrera);
                if(!carrera.isEmpty()){
                    alumnoEnviado.setCarrera(carrera.get());
                    alumnoEnviado.setPersona(persona.get());
                    Alumno alumno = alumnoRepository.save(alumnoEnviado);
                    data.add(alumno);
                    if(alumno !=null){
                        respuesta.setMensaje("SE REGISTRO ALUMNO CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                    }else{
                        respuesta.setMensaje("NO SE REGISTRO ALUMNO CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado= HttpStatus.BAD_REQUEST;
                    }
                }else{
                    respuesta.setMensaje("NO SE REGISTRO ALUMNO CORRECTAMENTE DEBIDO A QUE EL ID DE CARRERA ENVIADO NO SE ENCONTRO EN LA BASE");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                respuesta.setMensaje("NO SE REGISTRO ALUMNO CORRECTAMENTE DEBIDO A QUE EL ID DE PERSONA ENVIADO NO SE ENCONTRO EN LA BASE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar ALUMNO, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

  /*  @PostMapping("/EditarAlumno/{cedula}")
    public ResponseEntity<RespuestaGenerica> EditarAlumno(@RequestBody Alumno alumnoEnviado,@PathVariable String cedula){
        List<Alumno> data = new ArrayList<>();
        RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            alumnoEnviado.setPersona(persona);

            if(persona!= null){


            }else{
                respuesta.setMensaje("Hubo un problema al editar ALUMNO,DEDIDO A QUE LA CEDULA INGRESADA NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar ALUMNO, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }
*/

}
