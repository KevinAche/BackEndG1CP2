package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Asignaturas;
import Grupo1.BackEndG1CP2.Models.Evidencias;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.AsignaturasRepository;
import Grupo1.BackEndG1CP2.Repositories.EvidenciasRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionEvidencias")
public class EvidenciasController {

    @Autowired
    private EvidenciasRespository evidenciasRespository;

    @GetMapping("/ListaEvidencias")
    public ResponseEntity<RespuestaGenerica> ListarEvidencias(){
        List<Evidencias> data = new ArrayList<>();
        RespuestaGenerica<Evidencias> respuesta = new RespuestaGenerica<>();
        try {
            data=evidenciasRespository.findAll();
            respuesta.setMensaje("Se genero Evidencias Asignaturas");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Evidencias, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearEvidencias")
    public ResponseEntity<RespuestaGenerica> CrearEvidencias(@RequestBody Evidencias evidenciasEnviado){
        List<Evidencias> data = new ArrayList<>();
        RespuestaGenerica<Evidencias> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Evidencias evidencias = evidenciasRespository.save(evidenciasEnviado);
            data.add(evidencias);
            if(evidencias !=null){
                respuesta.setMensaje("SE REGISTRO Evidencias CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Evidencias CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Evidencias, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarEvidencias/{id}")
    public ResponseEntity<RespuestaGenerica> EditarEvidencias(@RequestBody Evidencias evidenciasEnviado,@PathVariable Long id){
        List<Evidencias> data = new ArrayList<>();
        RespuestaGenerica<Evidencias> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Evidencias evid = evidenciasRespository.findById(id)
                    .map(res ->{
                        res.setAnexos(evidenciasEnviado.getAnexos());
                        res.setDescripcion(evidenciasEnviado.getDescripcion());
                        res.setInformeCulminacion(evidenciasEnviado.getInformeCulminacion());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Evidencias CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return evidenciasRespository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Evidencias CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Evidencias();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Evidencias, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarEvidencias/{id}")
    public ResponseEntity EliminarEvidencias (@PathVariable Long id ){
        List<Evidencias> data = new ArrayList<Evidencias>();
        RespuestaGenerica<Evidencias> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            evidenciasRespository.deleteById(id);
            if(evidenciasRespository!=null){
                data.add(new Evidencias());
                respuesta.setMensaje("SE ELIMINO Evidencias CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Evidencias CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Evidencias, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
