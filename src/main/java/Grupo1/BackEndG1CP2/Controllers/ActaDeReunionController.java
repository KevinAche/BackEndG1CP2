package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.ActaDeReunion;
import Grupo1.BackEndG1CP2.Models.Convocatoria;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActaReunionRepository;
import Grupo1.BackEndG1CP2.Repositories.ConvocatoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionActaDeReunion")
public class ActaDeReunionController {

    @Autowired
    private ActaReunionRepository actaReunionRepository;

    @GetMapping("/ListaActaDeReunion")
    public ResponseEntity<RespuestaGenerica> ListarActaDeReunion(){
        List<ActaDeReunion> data = new ArrayList<>();
        RespuestaGenerica<ActaDeReunion> respuesta = new RespuestaGenerica<>();
        try {
            data=actaReunionRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO ActaDeReunion EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO ActaDeReunion, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearActaDeReunion")
    public ResponseEntity<RespuestaGenerica> CrearActaDeReunion(@RequestBody ActaDeReunion actaDeReunionEnviada){
        List<ActaDeReunion> data = new ArrayList<>();
        RespuestaGenerica<ActaDeReunion> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            ActaDeReunion actaDeReunion = actaReunionRepository.save(actaDeReunionEnviada);
            data.add(actaDeReunion);
            if(actaDeReunion !=null){
                respuesta.setMensaje("SE REGISTRO ActaDeReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO ActaDeReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar ActaDeReunion, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarActaDeReunion/{id}")
    public ResponseEntity<RespuestaGenerica> EditarActaDeReunion(@RequestBody ActaDeReunion actaDeReunionEnviada,@PathVariable Long id){
        List<ActaDeReunion> data = new ArrayList<>();
        RespuestaGenerica<ActaDeReunion> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            ActaDeReunion act = actaReunionRepository.findById(id)
                    .map(res ->{
                        res.setFechaEmision(actaDeReunionEnviada.getFechaEmision());
                        res.setDocActaReunion(actaDeReunionEnviada.getDocActaReunion());
                        res.setFechaFinPPP(actaDeReunionEnviada.getFechaFinPPP());
                        res.setHorario(actaDeReunionEnviada.getHorario());
                        res.setFechaInicioPPP(actaDeReunionEnviada.getFechaInicioPPP());
                        res.setNotificacionTA(actaDeReunionEnviada.getNotificacionTA());
                        res.setRespuestaEstudiante(actaDeReunionEnviada.getRespuestaEstudiante());
                        res.setAlumno(actaDeReunionEnviada.getAlumno());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO ActaDeReunion CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Convocatoria MODIFICADA
                        return actaReunionRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO ActaDeReunion CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new ActaDeReunion();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR ActaDeReunion, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarActaDeReunion/{id}")
    public ResponseEntity EliminarActaDeReunion (@PathVariable Long id ){
        List<ActaDeReunion> data = new ArrayList<ActaDeReunion>();
        RespuestaGenerica<ActaDeReunion> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            actaReunionRepository.deleteById(id);
            if(actaReunionRepository!=null){
                data.add(new ActaDeReunion());
                respuesta.setMensaje("SE ELIMINO ActaDeReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO ActaDeReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR ActaDeReunion, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
