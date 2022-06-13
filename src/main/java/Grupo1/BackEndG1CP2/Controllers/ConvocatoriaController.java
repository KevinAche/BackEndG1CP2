package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Convocatoria;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ConvocatoriaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionConvocatoria")
public class ConvocatoriaController {

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @GetMapping("/ListaConvocatoria")
    public ResponseEntity<RespuestaGenerica> ListarConvocatoria(){
        List<Convocatoria> data = new ArrayList<>();
        RespuestaGenerica<Convocatoria> respuesta = new RespuestaGenerica<>();
        try {
            data=convocatoriaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Convocatoria EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Convocatoria, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearConvocatoria")
    public ResponseEntity<RespuestaGenerica> CrearConvocatoria(@RequestBody Convocatoria convocatoriaEnviada){
        List<Convocatoria> data = new ArrayList<>();
        RespuestaGenerica<Convocatoria> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Convocatoria convocatoria = convocatoriaRepository.save(convocatoriaEnviada);
            data.add(convocatoria);
            if(convocatoria !=null){
                respuesta.setMensaje("SE REGISTRO Convocatoria CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Convocatoria CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Convocatoria, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarConvocatoria/{id}")
    public ResponseEntity<RespuestaGenerica> EditarConvocatoria(@RequestBody Convocatoria convocatoriaEnviada,@PathVariable Long id){
        List<Convocatoria> data = new ArrayList<>();
        RespuestaGenerica<Convocatoria> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Convocatoria con = convocatoriaRepository.findById(id)
                    .map(res ->{
                        res.setFechaEmision(convocatoriaEnviada.getFechaEmision());
                        res.setFechaMaxima(convocatoriaEnviada.getFechaMaxima());
                        res.setSolicitudEmpresa(convocatoriaEnviada.getSolicitudEmpresa());
                        res.setDocConvocatoria(convocatoriaEnviada.getDocConvocatoria());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Convocatoria CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Convocatoria MODIFICADA
                        return convocatoriaRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Convocatoria CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Convocatoria();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Convocatoria, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarConvocatoria/{id}")
    public ResponseEntity EliminarConvocatoria (@PathVariable Long id ){
        List<Convocatoria> data = new ArrayList<Convocatoria>();
        RespuestaGenerica<Convocatoria> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            convocatoriaRepository.deleteById(id);
            if(convocatoriaRepository!=null){
                data.add(new Convocatoria());
                respuesta.setMensaje("SE ELIMINO Convocatoria CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Convocatoria CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Convocatoria, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
