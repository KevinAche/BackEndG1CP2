package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.SolicitudAlumno;
import Grupo1.BackEndG1CP2.Models.SolicitudEmpresa;
import Grupo1.BackEndG1CP2.Repositories.SolicitudAlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionSolicitudAlumno")
public class SolicitudAlumnoControler {

    @Autowired
    private SolicitudAlumnoRepository solicitudAlumnoRepository;

    @GetMapping("/ListaSolicitudAlumno")
    public ResponseEntity<RespuestaGenerica> ListarSolicitudAlumno(){
        List<SolicitudAlumno> data = new ArrayList<>();
        RespuestaGenerica<SolicitudAlumno> respuesta = new RespuestaGenerica<>();
        try {
            data=solicitudAlumnoRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO SOLICITUDES EMPRESA");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO SOLICITUDES EMPRESA, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }
    
    @PostMapping("/CrearSolicitudAlumno")
    public ResponseEntity<RespuestaGenerica> CrearSolicitudAlumno(@RequestBody SolicitudAlumno solicitudEnviado){
        List<SolicitudAlumno> data = new ArrayList<>();
        RespuestaGenerica<SolicitudAlumno> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            SolicitudAlumno solicitud = solicitudAlumnoRepository.save(solicitudEnviado);
            data.add(solicitud);
            if(solicitud !=null){
                respuesta.setMensaje("SE REGISTRO LA SOLICITUD DEL ALUMNO CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO LA SOLICITUD DEL ALUMNO CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar SOLICITUD ALUMNO, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }
    
    @PutMapping("/EditarSolicitudAlumno/{id}")
    public ResponseEntity<RespuestaGenerica> EditarSolicitudAlumno(@RequestBody SolicitudAlumno solicitudEnviado,@PathVariable Long id){
        List<SolicitudAlumno> data = new ArrayList<>();
        RespuestaGenerica<SolicitudAlumno> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            SolicitudAlumno solicitud = solicitudAlumnoRepository.findById(id)
            		.map( soli ->{
            			
            			soli.setFechaEmision(solicitudEnviado.getFechaEmision());
            			soli.setConvocatoria(solicitudEnviado.getConvocatoria());
            			soli.setHorasPPP(solicitudEnviado.getHorasPPP());
            			soli.setEstado(solicitudEnviado.getEstado());
            			soli.setAlumno(solicitudEnviado.getAlumno());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(soli);
                        respuesta.setMensaje("SE MODIFICO SOLICITUD CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA SOLICITUD MODIFICADA
                        return solicitudAlumnoRepository.save(soli);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO SOLICITUD DE ALUMNO CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new SolicitudAlumno();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR SOLICITUD DE ALUMNO, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }
    
    @DeleteMapping("/EliminarSolicitudAlumno/{id}")
    public ResponseEntity EliminarSolicitudAlumno (@PathVariable Long id ){
        List<SolicitudAlumno> data = new ArrayList<SolicitudAlumno>();
        RespuestaGenerica<SolicitudAlumno> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            solicitudAlumnoRepository.deleteById(id);
            if(solicitudAlumnoRepository!=null){
                data.add(new SolicitudAlumno());
                respuesta.setMensaje("SE ELIMINO SOLICITUD ALUMNO CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO SOLICITUD ALUMNO CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR SOLICITUD ALUMNO, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
