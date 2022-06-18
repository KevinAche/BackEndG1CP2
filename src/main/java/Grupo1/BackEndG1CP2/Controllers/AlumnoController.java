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
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
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

    @GetMapping("/ListaDatosAlumnos")
    public ResponseEntity<RespuestaGenerica> ListaDatosAlumnos(){
        List<Alumno> data = new ArrayList<>();
        RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();
        try{
            data= alumnoRepository.findAll();
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
            if(persona.isEmpty()){
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


    @PutMapping("/EditarAlumno/{cedula}")
    public ResponseEntity<RespuestaGenerica> EditarAlumno(@RequestBody Alumno alumnoEnviado,@PathVariable String cedula){
        List<Alumno> data = new ArrayList<>();
        RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){
                List<Alumno> listaAlumnos = alumnoRepository.findAll();
                for (Alumno al: listaAlumnos) {
                    if(al.getPersona().getCedula()==persona.getCedula()){
                        alumnoEnviado.setCarrera(al.getCarrera());
                        alumnoEnviado.setPersona(al.getPersona());
                        alumnoEnviado.setIdAlumno(al.getIdAlumno());
                    }
                }
                Alumno alumno = alumnoRepository.findById(alumnoEnviado.getIdAlumno()).map(res ->{
                    res.setCiclo(alumnoEnviado.getCiclo());
                    res.setParalelo(alumnoEnviado.getParalelo());
                    res.setPromedio(alumnoEnviado.getPromedio());
                    res.setCarrera(alumnoEnviado.getCarrera());
                    res.setPersona(alumnoEnviado.getPersona());
                    data.add(res);
                    respuesta.setMensaje("SE MODIFICO ALUMNO CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                    return alumnoRepository.save(res);
                }).orElseGet(()->{
                    respuesta.setMensaje("NO SE MODIFICO EL ALUMNO");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                    return new Alumno();
                });
            }else{
                respuesta.setMensaje("EL ALUMNO NO PUDO SER MODIFICADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado.set(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR ALUMNO, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarAlumno/{cedula}")
    public ResponseEntity<RespuestaGenerica> EliminarAlumno(@PathVariable String cedula){
        List<Alumno> data = new ArrayList<Alumno>();
        RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){
                List<Alumno> listaAlumnos = alumnoRepository.findAll();
                Alumno alumno = new Alumno();
                for (Alumno al: listaAlumnos) {
                    if(persona.getCedula()==al.getPersona().getCedula()){
                        alumno=al;
                    }
                }
                if(alumno.getPersona().getCedula().equals(cedula)){
                     alumnoRepository.deleteById(alumno.getIdAlumno());
                     personaRepository.deleteById(persona.getIdPersona());
                    data.add(new Alumno());
                    respuesta.setMensaje("SE ELIMINO ALUMNO CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);

                }else{
                    data.add(null);
                    respuesta.setMensaje("EL ALUMNO NO PUDO SER ELIMINADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA ALUM");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                data.add(null);
                respuesta.setMensaje("EL ALUMNO NO PUDO SER ELIMINADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR ALUMNO, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }


    @GetMapping("/BuscarAlumnoCedula/{cedula}")
    public ResponseEntity<RespuestaGenerica> BuscarDocenteCedula(@PathVariable String cedula){
        List<Alumno> data = new ArrayList<Alumno>();
        RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){
                Alumno alumno = alumnoRepository.findByPersona(persona);
                if(alumno.getPersona().getCedula().equals(cedula)){
                    data.add(alumno);
                    respuesta.setMensaje("ALUMNO ENCONTRADO CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                }else{
                    data.add(null);
                    respuesta.setMensaje("EL ALUMNO NO FUE ENCONTRADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA ALUM");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                data.add(null);
                respuesta.setMensaje("EL ALUMNO NO PUDO SER ENCONTRADO DEBIDO QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al BUSCAR DOCENTE, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }


}
