package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Asignaturas;
import Grupo1.BackEndG1CP2.Models.Informe_Visita;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.AsignaturasRepository;
import Grupo1.BackEndG1CP2.Repositories.InformVisitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionInforme_Visita")
public class InformeVisitaController {

    @Autowired
    private InformVisitaRepository informVisitaRepository;

    @GetMapping("/ListaInforme_Visita")
    public ResponseEntity<RespuestaGenerica> ListarInforme_Visita(){
        List<Informe_Visita> data = new ArrayList<>();
        RespuestaGenerica<Informe_Visita> respuesta = new RespuestaGenerica<>();
        try {
            data=informVisitaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Informe_Visita");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Informe_Visita, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearInforme_Visita")
    public ResponseEntity<RespuestaGenerica> CrearInforme_Visita(@RequestBody Informe_Visita informeVisitaEnviado){
        List<Informe_Visita> data = new ArrayList<>();
        RespuestaGenerica<Informe_Visita> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Informe_Visita informeVisita = informVisitaRepository.save(informeVisitaEnviado);
            data.add(informeVisita);
            if(informeVisita !=null){
                respuesta.setMensaje("SE REGISTRO Informe_Visita CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Informe_Visita CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Informe_Visita, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarInforme_Visita/{id}")
    public ResponseEntity<RespuestaGenerica> EditarInforme_Visita(@RequestBody Informe_Visita informeVisitaEnviado,@PathVariable Long id){
        List<Informe_Visita> data = new ArrayList<>();
        RespuestaGenerica<Informe_Visita> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Informe_Visita infv = informVisitaRepository.findById(id)
                    .map(res ->{
                        res.setFecha(informeVisitaEnviado.getFecha());
                        res.setAsunto(informeVisitaEnviado.getAsunto());
                        res.setObservaciones(informeVisitaEnviado.getObservaciones());
                        res.setHoraInicio(informeVisitaEnviado.getHoraInicio());
                        res.setHoraFin(informeVisitaEnviado.getHoraFin());
                        res.setRegistroVisitaEmpresa(informeVisitaEnviado.getRegistroVisitaEmpresa());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO Informe_Visita CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return informVisitaRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO Informe_Visita CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Informe_Visita();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR Informe_Visita, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarInforme_Visita/{id}")
    public ResponseEntity EliminarInforme_Visita (@PathVariable Long id ){
        List<Informe_Visita> data = new ArrayList<Informe_Visita>();
        RespuestaGenerica<Informe_Visita> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            informVisitaRepository.deleteById(id);
            if(informVisitaRepository!=null){
                data.add(new Informe_Visita());
                respuesta.setMensaje("SE ELIMINO Informe_Visita CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO Informe_Visita CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR Informe_Visita, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
