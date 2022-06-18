package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Notificaciones;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.NotificacionesRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionNotificaciones")
public class NotificacionesController {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/ListaNotificaciones")
    public ResponseEntity<RespuestaGenerica> ListarNotificaciones(){
        List<Notificaciones> data = new ArrayList<>();
        RespuestaGenerica<Notificaciones> respuesta = new RespuestaGenerica<>();
        try {
            data=notificacionesRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Notificaciones");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Notificaciones, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/ListaNotificaciones/{cedula}")
    public ResponseEntity<RespuestaGenerica> ListarNotificacionesByPersona(@PathVariable String cedula){
        List<Notificaciones> data = new ArrayList<>();
        RespuestaGenerica<Notificaciones> respuesta = new RespuestaGenerica<>();
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            data=notificacionesRepository.findByPersona(persona);
            respuesta.setMensaje("Se genero LISTADO Notificaciones");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Notificaciones, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearNotificaciones")
    public ResponseEntity<RespuestaGenerica> CrearNotificaciones(@RequestBody Notificaciones notificacionesEnviado){
        List<Notificaciones> data = new ArrayList<>();
        RespuestaGenerica<Notificaciones> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Notificaciones notificaciones = notificacionesRepository.save(notificacionesEnviado);
            data.add(notificaciones);
            if(notificaciones !=null){
                respuesta.setMensaje("SE REGISTRO Notificaciones CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Notificaciones CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Notificaciones, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarNotificaciones/{id}")
    public ResponseEntity<RespuestaGenerica> EditarNotificaciones(@RequestBody Notificaciones notificacionesEnviado,@PathVariable Long id){
        List<Notificaciones> data = new ArrayList<>();
        RespuestaGenerica<Notificaciones> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Notificaciones noti = notificacionesRepository.findById(id)
                    .map(res ->{
                        res.setNotificacion(notificacionesEnviado.getNotificacion());
                        res.setPersona(notificacionesEnviado.getPersona());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Notificaciones CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return notificacionesRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Notificaciones CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Notificaciones();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Notificaciones, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarNotificaciones/{id}")
    public ResponseEntity EliminarNotificaciones (@PathVariable Long id ){
        List<Notificaciones> data = new ArrayList<Notificaciones>();
        RespuestaGenerica<Notificaciones> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            notificacionesRepository.deleteById(id);
            if(notificacionesRepository!=null){
                data.add(new Notificaciones());
                respuesta.setMensaje("SE ELIMINO Notificaciones CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Notificaciones CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Notificaciones, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
