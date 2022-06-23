package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Acuerdos;
import Grupo1.BackEndG1CP2.Models.Asignaturas;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.AcuerdosRepository;
import Grupo1.BackEndG1CP2.Repositories.AsignaturasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionAcuerdos")
public class AcuerdosController {

    @Autowired
    private AcuerdosRepository acuerdosRepository;

    @GetMapping("/ListaAcuerdos")
    public ResponseEntity<RespuestaGenerica> ListarAcuerdos(){
        List<Acuerdos> data = new ArrayList<>();
        RespuestaGenerica<Acuerdos> respuesta = new RespuestaGenerica<>();
        try {
            data=acuerdosRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Acuerdos");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Acuerdos, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearAcuerdos")
    public ResponseEntity<RespuestaGenerica> CrearAcuerdos(@RequestBody Acuerdos acuerdosEnviado){
        List<Acuerdos> data = new ArrayList<>();
        RespuestaGenerica<Acuerdos> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Acuerdos acuerdos = acuerdosRepository.save(acuerdosEnviado);
            data.add(acuerdos);
            if(acuerdos !=null){
                respuesta.setMensaje("SE REGISTRO Acuerdos CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Acuerdos CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Acuerdos, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarAcuerdos/{id}")
    public ResponseEntity<RespuestaGenerica> EditarAcuerdos(@RequestBody Acuerdos acuerdosEnviado,@PathVariable Long id){
        List<Acuerdos> data = new ArrayList<>();
        RespuestaGenerica<Acuerdos> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Acuerdos acu = acuerdosRepository.findById(id)
                    .map(res ->{
                        res.setDescripcion(acuerdosEnviado.getDescripcion());
                        res.setActaDeReunion(acuerdosEnviado.getActaDeReunion());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Acuerdos CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return acuerdosRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Acuerdos CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Acuerdos();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Acuerdos, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarAcuerdos/{id}")
    public ResponseEntity EliminarAcuerdos (@PathVariable Long id ){
        List<Acuerdos> data = new ArrayList<Acuerdos>();
        RespuestaGenerica<Acuerdos> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            acuerdosRepository.deleteById(id);
            if(acuerdosRepository!=null){
                data.add(new Acuerdos());
                respuesta.setMensaje("SE ELIMINO Acuerdos CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Acuerdos CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Acuerdos, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
