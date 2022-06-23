package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Evaluacion_TE;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.EvaluacionA12Repository;
import Grupo1.BackEndG1CP2.Repositories.EvaluacionTERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionEvaluacionTE")
public class EvaluacionTEController {

    @Autowired
    private EvaluacionTERepository evaluacionTERepository;

    @GetMapping("/ListaActividades")
    public ResponseEntity<RespuestaGenerica> ListarEvaluacion_TE(){
        List<Evaluacion_TE> data = new ArrayList<>();
        RespuestaGenerica<Evaluacion_TE> respuesta = new RespuestaGenerica<>();
        try {
            data=evaluacionTERepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Evaluacion_TE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Evaluacion_TE, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearEvaluacion_TE")
    public ResponseEntity<RespuestaGenerica> CrearEvaluacion_TE(@RequestBody Evaluacion_TE evaluacionTeEnviado){
        List<Evaluacion_TE> data = new ArrayList<>();
        RespuestaGenerica<Evaluacion_TE> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Evaluacion_TE evaluacionTe = evaluacionTERepository.save(evaluacionTeEnviado);
            data.add(evaluacionTe);
            if(evaluacionTe !=null){
                respuesta.setMensaje("SE REGISTRO Evaluacion_TE CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Evaluacion_TE CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Evaluacion_TE, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarEvaluacion_TE/{id}")
    public ResponseEntity<RespuestaGenerica> EditarEvaluacion_TE(@RequestBody Evaluacion_TE evaluacionTeEnviado,@PathVariable Long id){
        List<Evaluacion_TE> data = new ArrayList<>();
        RespuestaGenerica<Evaluacion_TE> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Evaluacion_TE evte = evaluacionTERepository.findById(id)
                    .map(res ->{
                        res.setDocEvaluacionTE(evaluacionTeEnviado.getDocEvaluacionTE());
                        res.setPuntajeTotal(evaluacionTeEnviado.getPuntajeTotal());
                        res.setTutorEmpresarial(evaluacionTeEnviado.getTutorEmpresarial());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Evaluacion_TE CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return evaluacionTERepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Evaluacion_TE CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Evaluacion_TE();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Evaluacion_TE, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarEvaluacion_TE/{id}")
    public ResponseEntity EliminarEvaluacion_TE (@PathVariable Long id ){
        List<Evaluacion_TE> data = new ArrayList<Evaluacion_TE>();
        RespuestaGenerica<Evaluacion_TE> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            evaluacionTERepository.deleteById(id);
            if(evaluacionTERepository!=null){
                data.add(new Evaluacion_TE());
                respuesta.setMensaje("SE ELIMINO Evaluacion_TE CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Evaluacion_TE CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Evaluacion_TE, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
