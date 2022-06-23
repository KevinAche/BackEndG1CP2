package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.ActividadesAReunion;
import Grupo1.BackEndG1CP2.Models.Empresa;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaActividadesEmpresa;
import Grupo1.BackEndG1CP2.Repositories.ActividadesAReunionRepository;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.EmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarActividadesEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionActividadesAReunion")
public class ActividadesAReunionController {

    @Autowired
    private ActividadesAReunionRepository actividadesAReunionRepository;


    @GetMapping("/ListaActividadesAReunion")
    public ResponseEntity<RespuestaGenerica> ListarActividadesAReunion(){
        List<ActividadesAReunion> data = new ArrayList<>();
        RespuestaGenerica<ActividadesAReunion> respuesta = new RespuestaGenerica<>();
        try {
            data=actividadesAReunionRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO ActividadesAReunion");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO ActividadesAReunion, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearActividadesAReunion")
    public ResponseEntity<RespuestaGenerica> CrearActividades(@RequestBody ActividadesAReunion actividadesEnviado){
        List<ActividadesAReunion> data = new ArrayList<>();
        RespuestaGenerica<ActividadesAReunion> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            ActividadesAReunion actividades = actividadesAReunionRepository.save(actividadesEnviado);
            data.add(actividades);
            if(actividades !=null){
                respuesta.setMensaje("SE REGISTRO ActividadesAReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO ActividadesAReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar ActividadesAReunion, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarActividadesAReunion/{id}")
    public ResponseEntity<RespuestaGenerica> EditarActividadesAReunion(@RequestBody ActividadesAReunion actividadesEnviado,@PathVariable Long id){
        List<ActividadesAReunion> data = new ArrayList<>();
        RespuestaGenerica<ActividadesAReunion> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            ActividadesAReunion act = actividadesAReunionRepository.findById(id)
                    .map(res ->{
                        res.setActividades(actividadesEnviado.getActividades());
                        res.setNumHoras(actividadesEnviado.getNumHoras());
                        res.setNumSemanas(actividadesEnviado.getNumSemanas());
                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO ActividadesAReunion CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return actividadesAReunionRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO ActividadesAReunion CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new ActividadesAReunion();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR ActividadesAReunion, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarActividadesAReunion/{id}")
    public ResponseEntity EliminarActividadesAReunion (@PathVariable Long id ){
        List<ActividadesAReunion> data = new ArrayList<ActividadesAReunion>();
        RespuestaGenerica<ActividadesAReunion> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            actividadesAReunionRepository.deleteById(id);
            if(actividadesAReunionRepository!=null){
                data.add(new ActividadesAReunion());
                respuesta.setMensaje("SE ELIMINO ActividadesAReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO ActividadesAReunion CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR ActividadesAReunion, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
