package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Cronograma;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.CronogramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionCronograma")
public class CronogramaController {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @GetMapping("/ListaActividades")
    public ResponseEntity<RespuestaGenerica> ListarCronograma(){
        List<Cronograma> data = new ArrayList<>();
        RespuestaGenerica<Cronograma> respuesta = new RespuestaGenerica<>();
        try {
            data=cronogramaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Cronograma");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Cronograma, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearCronograma")
    public ResponseEntity<RespuestaGenerica> CrearCronograma(@RequestBody Cronograma cronogramaEnviado){
        List<Cronograma> data = new ArrayList<>();
        RespuestaGenerica<Cronograma> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Cronograma cronograma = cronogramaRepository.save(cronogramaEnviado);
            data.add(cronograma);
            if(cronograma !=null){
                respuesta.setMensaje("SE REGISTRO Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Cronograma, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarCronograma/{id}")
    public ResponseEntity<RespuestaGenerica> EditarCronograma(@RequestBody Cronograma cronogramaEnviado,@PathVariable Long id){
        List<Cronograma> data = new ArrayList<>();
        RespuestaGenerica<Cronograma> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Cronograma crn = cronogramaRepository.findById(id)
                    .map(res ->{
                        res.setDocCronograma(cronogramaEnviado.getDocCronograma());
                        res.setTutorAcademico(cronogramaEnviado.getTutorAcademico());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Cronograma CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Cronograma MODIFICADA
                        return cronogramaRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Cronograma CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Cronograma();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Cronograma, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarCronograma/{id}")
    public ResponseEntity EliminarCronograma (@PathVariable Long id ){
        List<Cronograma> data = new ArrayList<Cronograma>();
        RespuestaGenerica<Cronograma> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            cronogramaRepository.deleteById(id);
            if(cronogramaRepository!=null){
                data.add(new Cronograma());
                respuesta.setMensaje("SE ELIMINO Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Cronograma, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
