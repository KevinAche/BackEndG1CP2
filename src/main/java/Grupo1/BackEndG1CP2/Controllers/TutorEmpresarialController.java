package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.*;
import Grupo1.BackEndG1CP2.Repositories.AlumnoRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.TutorEmpresarialRepository;
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
@RequestMapping("/GestionTutorEmpresarial")
public class TutorEmpresarialController {

    @Autowired
    private TutorEmpresarialRepository tutorEmpresarialRepository;

    @Autowired
    private PersonalEmpresaRepository personalEmpresaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping("/ListarTutoresEmp")
    public ResponseEntity<RespuestaGenerica> ListarTutoresEmpresarial(){
        List<TutorEmpresarial> data = new ArrayList<>();
        RespuestaGenerica<TutorEmpresarial> respuesta = new RespuestaGenerica<>();
        try {
            data=tutorEmpresarialRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO TUTORES EMPRESARIALES EXITOSAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO TUTORES EMPRESARIALES, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearTutorEmpresarial/{cedulaT}/{cedulaA}")
    public ResponseEntity<RespuestaGenerica> CrearTutorEmpresarial(@RequestBody TutorEmpresarial tutorEmpresarialEnviado ,@PathVariable String cedulaT,@PathVariable String cedulaA){
        List<TutorEmpresarial> data = new ArrayList<>();
        RespuestaGenerica<TutorEmpresarial> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Persona personaA = personaRepository.findByCedula(cedulaA);
            Persona personaT = personaRepository.findByCedula(cedulaT);
            Alumno alumno= alumnoRepository.findByPersona(personaA);
            PersonalEmpresa personalEmpresa = personalEmpresaRepository.findByPersona(personaT);

            tutorEmpresarialEnviado.setPersonalEmpresa(personalEmpresa);
            tutorEmpresarialEnviado.setAlumno(alumno);

            TutorEmpresarial tutorEmpresarial = tutorEmpresarialRepository.save(tutorEmpresarialEnviado);
            data.add(tutorEmpresarial);
            if(tutorEmpresarial !=null){
                respuesta.setMensaje("SE REGISTRO TUTOR EMPRESARIAL CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO TUTOR EMPRESARIAL CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar TUTOR EMPRESARIAL, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


    @PutMapping("/EditarTutorEmpresarial/{id}")
    public ResponseEntity<RespuestaGenerica> EditarTutorEmpresarial(@RequestBody TutorEmpresarial tutorEmpresarialEnviado,@PathVariable Long id){
        List<TutorEmpresarial> data = new ArrayList<>();
        RespuestaGenerica<TutorEmpresarial> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            TutorEmpresarial tutE = tutorEmpresarialRepository.findById(id)
                    .map(res ->{
                        res.setAlumno(tutorEmpresarialEnviado.getAlumno());
                        res.setPersonalEmpresa(tutorEmpresarialEnviado.getPersonalEmpresa());
                        res.setDocAsignacion(tutorEmpresarialEnviado.getDocAsignacion());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO TUTOR EMPRESARIAL CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA TutorAcademico MODIFICADA
                        return tutorEmpresarialRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO TUTOR EMPRESARIAL CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new TutorEmpresarial();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR TUTOR EMPRESARIAL, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }




    @DeleteMapping("/EliminarTutorEmpresarial/{id}")
    public ResponseEntity EliminarTutorAcademico (@PathVariable Long id ){
        List<TutorEmpresarial> data = new ArrayList<TutorEmpresarial>();
        RespuestaGenerica<TutorEmpresarial> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            tutorEmpresarialRepository.deleteById(id);
            if(tutorEmpresarialRepository!=null){
                data.add(new TutorEmpresarial());
                respuesta.setMensaje("SE ELIMINO TUTOR EMPRESARIAL CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO TUTOR EMPRESARIAL CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR TUTOR EMPRESARIAL, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }


    @GetMapping("/BuscarTutorEmp/{id}")
    public ResponseEntity BuscarTutorEmp (@PathVariable Long id ){
        List<TutorEmpresarial> data = new ArrayList<TutorEmpresarial>();
        RespuestaGenerica<TutorEmpresarial> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            Alumno alumno= alumnoRepository.findById(id).get();
            if (alumno!=null){
                TutorEmpresarial tutorEmpresarial =tutorEmpresarialRepository.findByAlumno(alumno);
                data.add(tutorEmpresarial);
                respuesta.setMensaje("SE ENCONTRO TUTOR EMPRESARIAL CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ENCONTRO TUTOR EMPRESARIAL DEBIDO A QUE EL ID DE ALUMNO MANDADO -> "+id+" NO EXISTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al BUSCAR TUTOR EMPRESARIAL, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }

}
