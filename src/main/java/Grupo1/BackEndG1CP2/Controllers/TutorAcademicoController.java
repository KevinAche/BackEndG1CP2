package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.*;
import Grupo1.BackEndG1CP2.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionTutorAcademico")
public class TutorAcademicoController {

    @Autowired
    private TutorAcademicoRepository tutorAcademicoRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/ListaTutorAcademico")
    public ResponseEntity<RespuestaGenerica> ListarTutorAcademico(){
        List<TutorAcademico> data = new ArrayList<>();
        RespuestaGenerica<TutorAcademico> respuesta = new RespuestaGenerica<>();
        try {
            data=tutorAcademicoRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO TutorAcademico");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO TutorAcademico, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearTutorAcademico/{cedulaD}/{cedulaA}")
    public ResponseEntity<RespuestaGenerica> CrearTutorAcademico(@RequestBody TutorAcademico tutorAcademicoEnviado ,@PathVariable String cedulaD,@PathVariable String cedulaA){
        List<TutorAcademico> data = new ArrayList<>();
        RespuestaGenerica<TutorAcademico> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona personaD = personaRepository.findByCedula(cedulaD);
            Persona personaA = personaRepository.findByCedula(cedulaA);

            Docente docente= docenteRepository.findByPersona(personaD);
            Alumno alumno= alumnoRepository.findByPersona(personaA);
            tutorAcademicoEnviado.setDocente(docente);
            tutorAcademicoEnviado.setAlumno(alumno);
            TutorAcademico tutorAcademico = tutorAcademicoRepository.save(tutorAcademicoEnviado);
            data.add(tutorAcademico);
            if(tutorAcademico !=null){
                respuesta.setMensaje("SE REGISTRO TutorAcademico CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO TutorAcademico CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar TutorAcademico, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarTutorAcademico/{id}")
    public ResponseEntity<RespuestaGenerica> EditarTutorAcademico(@RequestBody TutorAcademico tutorAcademicoEnviado,@PathVariable Long id){
        List<TutorAcademico> data = new ArrayList<>();
        RespuestaGenerica<TutorAcademico> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            TutorAcademico tutA = tutorAcademicoRepository.findById(id)
                    .map(res ->{
                        res.setAlumno(tutorAcademicoEnviado.getAlumno());
                        res.setDocente(tutorAcademicoEnviado.getDocente());
                        res.setDocAsignacion(tutorAcademicoEnviado.getDocAsignacion());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO TutorAcademico CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA TutorAcademico MODIFICADA
                        return tutorAcademicoRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO TutorAcademico CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new TutorAcademico();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR TutorAcademico, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarTutorAcademico/{id}")
    public ResponseEntity EliminarTutorAcademico (@PathVariable Long id ){
        List<TutorAcademico> data = new ArrayList<TutorAcademico>();
        RespuestaGenerica<TutorAcademico> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            tutorAcademicoRepository.deleteById(id);
            if(tutorAcademicoRepository!=null){
                data.add(new TutorAcademico());
                respuesta.setMensaje("SE ELIMINO TutorAcademico CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO TutorAcademico CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR TutorAcademico, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
