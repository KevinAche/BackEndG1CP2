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
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionActividades_Cronograma")
public class ActividadesCronogramaController {

    @Autowired
    private ActivCronogramRepository activCronogramRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Autowired
    private RegistroAsistenciaRepository registroAsistenciaRepository;

    @Autowired
    private ActividadesDiariasRepository actividadesDiariasRepository;

    @GetMapping("/ListaActividades_Cronograma")
    public ResponseEntity<RespuestaGenerica> ListarActividades_Cronograma(){
        List<Actividades_Cronograma> data = new ArrayList<>();
        RespuestaGenerica<Actividades_Cronograma> respuesta = new RespuestaGenerica<>();
        try {
            data=activCronogramRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Actividades_Cronograma");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Actividades_Cronograma, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/ListaActividadesPorCronograma/{id}")
    public ResponseEntity<RespuestaGenerica> ListarActividadesPorCronograma(@PathVariable Long id){
        List<Actividades_Cronograma> data = new ArrayList<>();
        RespuestaGenerica<Actividades_Cronograma> respuesta = new RespuestaGenerica<>();
        try {

            Cronograma cronograma = cronogramaRepository.findById(id).get();
            Registro_Asistencias registroAsistencias = registroAsistenciaRepository
                    .findByAlumno(cronograma.getTutorAcademico().getAlumno());

            List<Actividades_Diarias> actividadesDiarias = actividadesDiariasRepository.findByRegistroA(registroAsistencias);

            List<Actividades_Cronograma> actividadesCronograma = activCronogramRepository.findByCronograma(cronograma);

            for(Actividades_Diarias actD : actividadesDiarias){

                if(activCronogramRepository.findByActividadesDiarias(actD)== null){
                    Actividades_Cronograma actN= new Actividades_Cronograma();
                    actN.setActividadesDiarias(actD);
                    actN.setCronograma(cronograma);
                    activCronogramRepository.save(actN);
                }
            }

            data=activCronogramRepository.findByCronograma(cronograma);
            respuesta.setMensaje("Se genero LISTADO Actividades_Cronograma");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Actividades_Cronograma, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearActividades_Cronograma")
    public ResponseEntity<RespuestaGenerica> CrearActividades_Cronograma(@RequestBody Actividades_Cronograma actividadesCronogramaEnviado){
        List<Actividades_Cronograma> data = new ArrayList<>();
        RespuestaGenerica<Actividades_Cronograma> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Actividades_Cronograma actividades = activCronogramRepository.save(actividadesCronogramaEnviado);
            data.add(actividades);
            if(actividades !=null){
                respuesta.setMensaje("SE REGISTRO Actividades_Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Actividades_Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Actividades_Cronograma, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarActividades_Cronograma/{id}")
    public ResponseEntity<RespuestaGenerica> EditarActividades_Cronograma(@RequestBody Actividades_Cronograma actividadesCronogramaEnviado,@PathVariable Long id){
        List<Actividades_Cronograma> data = new ArrayList<>();
        RespuestaGenerica<Actividades_Cronograma> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Actividades_Cronograma act = activCronogramRepository.findById(id)
                    .map(res ->{
                        res.setNumActividad(actividadesCronogramaEnviado.getNumActividad());
                        res.setFechaSeguimiento(actividadesCronogramaEnviado.getFechaSeguimiento());
                        res.setFechaFinalizacion(actividadesCronogramaEnviado.getFechaFinalizacion());
                        res.setPorcentaje(actividadesCronogramaEnviado.getPorcentaje());
                        res.setObservacion(actividadesCronogramaEnviado.getObservacion());
                        res.setActividadesDiarias(actividadesCronogramaEnviado.getActividadesDiarias());
                        res.setCronograma(actividadesCronogramaEnviado.getCronograma());


                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Actividades_Cronograma CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades_Cronograma MODIFICADA
                        return activCronogramRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Actividades_Cronograma CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Actividades_Cronograma();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Actividades_Cronograma, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarActividades_Cronograma/{id}")
    public ResponseEntity EliminarActividades_Cronograma (@PathVariable Long id ){
        List<Actividades_Cronograma> data = new ArrayList<Actividades_Cronograma>();
        RespuestaGenerica<Actividades_Cronograma> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            activCronogramRepository.deleteById(id);
            if(activCronogramRepository!=null){
                data.add(new Actividades_Cronograma());
                respuesta.setMensaje("SE ELIMINO Actividades_Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Actividades_Cronograma CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Actividades_Cronograma, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
