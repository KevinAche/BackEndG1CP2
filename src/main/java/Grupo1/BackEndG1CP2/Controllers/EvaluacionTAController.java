package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Evaluacion_TA;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.EvaluacionTARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionActividades")
public class EvaluacionTAController {

    @Autowired
    private EvaluacionTARepository evaluacionTARepository;

    @GetMapping("/ListaEvaluacion_TA")
    public ResponseEntity<RespuestaGenerica> ListarEvaluacion_TA(){
        List<Evaluacion_TA> data = new ArrayList<>();
        RespuestaGenerica<Evaluacion_TA> respuesta = new RespuestaGenerica<>();
        try {
            data=evaluacionTARepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Evaluacion_TA");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Evaluacion_TA, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearEvaluacion_TA")
    public ResponseEntity<RespuestaGenerica> CrearEvaluacion_TA(@RequestBody Evaluacion_TA evaluacionTaEnviado){
        List<Evaluacion_TA> data = new ArrayList<>();
        RespuestaGenerica<Evaluacion_TA> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Evaluacion_TA evaluacionTa = evaluacionTARepository.save(evaluacionTaEnviado);
            data.add(evaluacionTa);
            if(evaluacionTa !=null){
                respuesta.setMensaje("SE REGISTRO Evaluacion_TA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Evaluacion_TA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Evaluacion_TA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarEvaluacion_TA/{id}")
    public ResponseEntity<RespuestaGenerica> EditarEvaluacion_TA(@RequestBody Evaluacion_TA evaluacionTaEnviado,@PathVariable Long id){
        List<Evaluacion_TA> data = new ArrayList<>();
        RespuestaGenerica<Evaluacion_TA> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Evaluacion_TA evta = evaluacionTARepository.findById(id)
                    .map(res ->{
                        res.setDocEvaluacionTA(evaluacionTaEnviado.getDocEvaluacionTA());
                        res.setPuntajeTotal(evaluacionTaEnviado.getPuntajeTotal());
                        res.setTutorAcademico(evaluacionTaEnviado.getTutorAcademico());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Evaluacion_TA CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return evaluacionTARepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Evaluacion_TA CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Evaluacion_TA();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Evaluacion_TA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarEvaluacion_TA/{id}")
    public ResponseEntity EliminarEvaluacion_TA (@PathVariable Long id ){
        List<Evaluacion_TA> data = new ArrayList<Evaluacion_TA>();
        RespuestaGenerica<Evaluacion_TA> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            evaluacionTARepository.deleteById(id);
            if(evaluacionTARepository!=null){
                data.add(new Evaluacion_TA());
                respuesta.setMensaje("SE ELIMINO Evaluacion_TA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Evaluacion_TA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Evaluacion_TA, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
