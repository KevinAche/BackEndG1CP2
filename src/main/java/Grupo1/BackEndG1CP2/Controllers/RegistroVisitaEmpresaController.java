package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Registro_VisitaEmpresa;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.RegistroVisitaEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionRegistroVisitaEmpresa")
public class RegistroVisitaEmpresaController {

    @Autowired
    private RegistroVisitaEmpresaRepository registroVisitaEmpresaRepository;

    @GetMapping("/ListaRegistro_VisitaEmpresa")
    public ResponseEntity<RespuestaGenerica> ListarRegistro_VisitaEmpresa(){
        List<Registro_VisitaEmpresa> data = new ArrayList<>();
        RespuestaGenerica<Registro_VisitaEmpresa> respuesta = new RespuestaGenerica<>();
        try {
            data=registroVisitaEmpresaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Registro_VisitaEmpresa");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Registro_VisitaEmpresa, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearRegistro_VisitaEmpresa")
    public ResponseEntity<RespuestaGenerica> CrearActividades(@RequestBody Registro_VisitaEmpresa registroVisitaEmpresaEnviado){
        List<Registro_VisitaEmpresa> data = new ArrayList<>();
        RespuestaGenerica<Registro_VisitaEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Registro_VisitaEmpresa registroVisitaEmpresa = registroVisitaEmpresaRepository.save(registroVisitaEmpresaEnviado);
            data.add(registroVisitaEmpresa);
            if(registroVisitaEmpresa !=null){
                respuesta.setMensaje("SE REGISTRO Registro_VisitaEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Registro_VisitaEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Registro_VisitaEmpresa, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarRegistro_VisitaEmpresa/{id}")
    public ResponseEntity<RespuestaGenerica> EditarRegistro_VisitaEmpresa(@RequestBody Registro_VisitaEmpresa registroVisitaEmpresaEnviado,@PathVariable Long id){
        List<Registro_VisitaEmpresa> data = new ArrayList<>();
        RespuestaGenerica<Registro_VisitaEmpresa> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Registro_VisitaEmpresa rve = registroVisitaEmpresaRepository.findById(id)
                    .map(res ->{
                        res.setDocRegistroVisita(registroVisitaEmpresaEnviado.getDocRegistroVisita());
                        res.setObservaciones(registroVisitaEmpresaEnviado.getObservaciones());
                        res.setAlumno(registroVisitaEmpresaEnviado.getAlumno());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Registro_VisitaEmpresa CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Registro_VisitaEmpresa MODIFICADA
                        return registroVisitaEmpresaRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Actividades CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Registro_VisitaEmpresa();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Registro_VisitaEmpresa, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarRegistro_VisitaEmpresa/{id}")
    public ResponseEntity EliminarRegistro_VisitaEmpresa (@PathVariable Long id ){
        List<Registro_VisitaEmpresa> data = new ArrayList<Registro_VisitaEmpresa>();
        RespuestaGenerica<Registro_VisitaEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            registroVisitaEmpresaRepository.deleteById(id);
            if(registroVisitaEmpresaRepository!=null){
                data.add(new Registro_VisitaEmpresa());
                respuesta.setMensaje("SE ELIMINO Registro_VisitaEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Registro_VisitaEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Registro_VisitaEmpresa, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
