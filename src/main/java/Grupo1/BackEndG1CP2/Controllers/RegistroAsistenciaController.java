package Grupo1.BackEndG1CP2.Controllers;


import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.Registro_Asistencias;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.AlumnoRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.RegistroAsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/RegistroAsistencia")
public class RegistroAsistenciaController {

    @Autowired
    private RegistroAsistenciaRepository registroAsistenciaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private PersonaRepository personaRepository;




    @GetMapping("/ListaRegAsistencias")
    public ResponseEntity<RespuestaGenerica> ListarRegAsistencias(){
        List<Registro_Asistencias> data = new ArrayList<>();
        RespuestaGenerica<Registro_Asistencias> respuesta = new RespuestaGenerica<>();
        try{
            data= registroAsistenciaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO REGISTRO DE ASISTENCIAS EXITOSAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO REGISTRO DE ASISTENCIAS, causa ->"+e.getCause()+" || message->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/CrearRegistroAsistencia/{cedula}")
    public ResponseEntity<RespuestaGenerica> CrearRegistro(@RequestBody Registro_Asistencias regisAsis, @PathVariable String cedula){
        List<Registro_Asistencias> data = new ArrayList<>();
        RespuestaGenerica<Registro_Asistencias> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            Alumno alumno = alumnoRepository.findByPersona(persona);
            if(persona != null){
                regisAsis.setAlumno(alumno);

                Registro_Asistencias regAsis = registroAsistenciaRepository.save(regisAsis);
                if(regAsis != null){

                    data.add(regAsis);
                    if(regAsis !=null){
                        respuesta.setMensaje("SE REGISTRO ASISTENCIA CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                    }else{
                        respuesta.setMensaje("NO SE REGISTRO ASISTENCIA CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado= HttpStatus.BAD_REQUEST;
                    }
                }else{
                    respuesta.setMensaje("NO SE REGISTRO ASISTENCIA CORRECTAMENTE DEBIDO A QUE EL ID DE CARRERA ENVIADO NO SE ENCONTRO EN LA BASE");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                respuesta.setMensaje("NO SE REGISTRO ASISTENCIA CORRECTAMENTE DEBIDO A QUE EL ID DE PERSONA ENVIADO NO SE ENCONTRO EN LA BASE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar ASISTENCIA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

}
