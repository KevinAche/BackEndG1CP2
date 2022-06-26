package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Convocatoria;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.SolicitudEmpresa;
import Grupo1.BackEndG1CP2.Models.Views.VistaListaConvocatorias;
import Grupo1.BackEndG1CP2.Repositories.ConvocatoriaRepository;
import Grupo1.BackEndG1CP2.Repositories.SolicitudEmpRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListaConvocatoriasRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionConvocatoria")
public class ConvocatoriaController {

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Autowired
    private SolicitudEmpRepository solicitudEmpRepository;

    @Autowired
    private ListaConvocatoriasRepository listaConvocatoriasRepository;

    @GetMapping("/ListaConvocatoria")
    public ResponseEntity<RespuestaGenerica> ListarConvocatoria(){
        List<Convocatoria> data = new ArrayList<>();
        RespuestaGenerica<Convocatoria> respuesta = new RespuestaGenerica<>();
        try {
            Date fechaActual = new Date();
            data=convocatoriaRepository.findAll();

            /*
            for(Convocatoria convocatoria: data){
                if(convocatoria.getFechaMaxima().compareTo(fechaActual)<0){
                    convocatoria.setEstado("CERRADA");
                    convocatoriaRepository.save(convocatoria);
                }
            }
            data=convocatoriaRepository.findAll();
*/
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


    @GetMapping("/ListaConvocatoriaVista")
    public ResponseEntity<RespuestaGenerica> ListarConvocatoriaVista(){
        List<VistaListaConvocatorias> data = new ArrayList<>();
        RespuestaGenerica<VistaListaConvocatorias> respuesta = new RespuestaGenerica<>();
        try {
            Date fechaActual = new Date();
            data=listaConvocatoriasRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Convocatoria VISTA EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Convocatoria VISTA , causa ->"+e.getCause()+" || messagge->"+e.getMessage());
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
            convocatoria.setEstado("ABIERTO");
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

    @PostMapping("/CrearConD/{id_solicitud}")
    public ResponseEntity<RespuestaGenerica> CrearConvocatoriaDatos(@RequestBody Convocatoria convocatoriaEnviada,@PathVariable Long id_solicitud){
        List<Convocatoria> data = new ArrayList<>();
        RespuestaGenerica<Convocatoria> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            SolicitudEmpresa solicitudEmpresa = solicitudEmpRepository.findById(id_solicitud).get();
            if(solicitudEmpresa!=null){
                convocatoriaEnviada.setEstado("ABIERTO");
                convocatoriaEnviada.setSolicitudEmpresa(solicitudEmpresa);
                Convocatoria convocatoria = convocatoriaRepository.save(convocatoriaEnviada);
                data.add(convocatoria);
                respuesta.setMensaje("SE REGISTRO CONVOCATORIA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO CONVOCATORIA CORRECTAMENTE");
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


    @GetMapping("/ObtenerNumCon")
    public ResponseEntity ObtenerNumero (){
        List<Long> data = new ArrayList<Long>();
        RespuestaGenerica<Long> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {
            List<Convocatoria> convocatorias = convocatoriaRepository.findAll();
            if(convocatorias.size()==0){
                data.add(1L);
            }else{
                convocatorias.sort((Convocatoria a,Convocatoria b)-> a.getIdConvocatoria().compareTo(b.getIdConvocatoria()));
                System.out.println(convocatorias.toString());
                Convocatoria con=convocatorias.get(convocatorias.size()-1);
                System.out.println(con.getIdConvocatoria());
                data.add(con.getIdConvocatoria()+1);
            }
            if(convocatorias.size()>=0){
                respuesta.setMensaje("SE GENERO EL PROXIMO ID DE CONVOCATORIA");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE GENERO EL PROXIMO ID DE CONVOCATORIA");
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
