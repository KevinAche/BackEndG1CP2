package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.*;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.DocenteRepository;
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
public class ActividadesController {

    @Autowired
    private ActividadesRepository actividadesRepository;

    @GetMapping("/ListaActividades")
    public ResponseEntity<RespuestaGenerica> ListarActividades(){
        List<Actividades> data = new ArrayList<>();
        RespuestaGenerica<Actividades> respuesta = new RespuestaGenerica<>();
        try {
            data=actividadesRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Actividades");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Actividades, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearActividades")
    public ResponseEntity<RespuestaGenerica> CrearActividades(@RequestBody Actividades actividadesEnviado){
        List<Actividades> data = new ArrayList<>();
        RespuestaGenerica<Actividades> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Actividades actividades = actividadesRepository.save(actividadesEnviado);
            data.add(actividades);
            if(actividades !=null){
                respuesta.setMensaje("SE REGISTRO Actividades CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Actividades CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Actividades, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarActividades/{id}")
    public ResponseEntity<RespuestaGenerica> EditarActividades(@RequestBody Actividades actividadesEnviado,@PathVariable Long id){
        List<Actividades> data = new ArrayList<>();
        RespuestaGenerica<Actividades> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Actividades act = actividadesRepository.findById(id)
                    .map(res ->{
                        res.setArea(actividadesEnviado.getArea());
                        res.setAsignatura(actividadesEnviado.getAsignatura());
                        res.setDescripcion(actividadesEnviado.getDescripcion());
                        res.setSolicitudEmpresa(actividadesEnviado.getSolicitudEmpresa());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Actividades CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return actividadesRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Actividades CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Actividades();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Actividades, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarActividades/{id}")
    public ResponseEntity EliminarActividades (@PathVariable Long id ){
        List<Actividades> data = new ArrayList<Actividades>();
        RespuestaGenerica<Actividades> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            actividadesRepository.deleteById(id);
            if(actividadesRepository!=null){
                data.add(new Actividades());
                respuesta.setMensaje("SE ELIMINO Actividades CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Actividades CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Actividades, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
