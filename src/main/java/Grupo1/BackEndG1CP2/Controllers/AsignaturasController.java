package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Asignaturas;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.AsignaturasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionAsignaturas")
public class AsignaturasController {

    @Autowired
    private AsignaturasRepository asignaturasRepository;

    @GetMapping("/ListaAsignaturas")
    public ResponseEntity<RespuestaGenerica> ListarAsignaturas(){
        List<Asignaturas> data = new ArrayList<>();
        RespuestaGenerica<Asignaturas> respuesta = new RespuestaGenerica<>();
        try {
            data=asignaturasRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Asignaturas");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Asignaturas, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearAsignaturas")
    public ResponseEntity<RespuestaGenerica> CrearAsignaturas(@RequestBody Asignaturas asignaturasEnviado){
        List<Asignaturas> data = new ArrayList<>();
        RespuestaGenerica<Asignaturas> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Asignaturas asignaturas = asignaturasRepository.save(asignaturasEnviado);
            data.add(asignaturas);
            if(asignaturas !=null){
                respuesta.setMensaje("SE REGISTRO Asignaturas CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Asignaturas CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Asignaturas, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarAsignaturas/{id}")
    public ResponseEntity<RespuestaGenerica> EditarAsignaturas(@RequestBody Asignaturas asignaturasEnviada,@PathVariable Long id){
        List<Asignaturas> data = new ArrayList<>();
        RespuestaGenerica<Asignaturas> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Asignaturas asig = asignaturasRepository.findById(id)
                    .map(res ->{
                        res.setNombreAsignatura(asignaturasEnviada.getNombreAsignatura());
                        res.setAbreviatura(asignaturasEnviada.getAbreviatura());
                        res.setCiclo(asignaturasEnviada.getCiclo());
                        res.setCarrera(asignaturasEnviada.getCarrera());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Asignaturas CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return asignaturasRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Asignaturas CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Asignaturas();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Asignaturas, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarAsignaturas/{id}")
    public ResponseEntity EliminarAsignaturas (@PathVariable Long id ){
        List<Asignaturas> data = new ArrayList<Asignaturas>();
        RespuestaGenerica<Asignaturas> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            asignaturasRepository.deleteById(id);
            if(asignaturasRepository!=null){
                data.add(new Asignaturas());
                respuesta.setMensaje("SE ELIMINO Asignaturas CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Asignaturas CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Asignaturas, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
