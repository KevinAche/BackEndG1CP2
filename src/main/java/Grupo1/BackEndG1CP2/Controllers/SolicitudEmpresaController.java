package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.SolicitudEmpresa;
import Grupo1.BackEndG1CP2.Repositories.EmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.SolicitudEmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionSolicitudEmpresa")
public class SolicitudEmpresaController {

    @Autowired
    private SolicitudEmpRepository solicitudEmpRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonalEmpresaRepository personalEmpresaRepository;

    @GetMapping("/ListaSolicitudEmpresa")
    public ResponseEntity<RespuestaGenerica> ListarSolicitudEmpresa(){
        List<SolicitudEmpresa> data = new ArrayList<>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        try {
            data=solicitudEmpRepository.findAll();
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


    @PostMapping("/CrearSolicitudEmpresa/{cedula}")
    public ResponseEntity<RespuestaGenerica> CrearSolicitudEmpresa(@RequestBody SolicitudEmpresa solicitudEmpresaEnviada ,@PathVariable String cedula){
        List<SolicitudEmpresa> data = new ArrayList<>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            PersonalEmpresa personalEmpresa= personalEmpresaRepository.findByPersona(persona);
            solicitudEmpresaEnviada.setEmpleado(personalEmpresa);
            SolicitudEmpresa solicitudEmpresa = solicitudEmpRepository.save(solicitudEmpresaEnviada);
            data.add(solicitudEmpresa);
            if(solicitudEmpresa !=null){
                respuesta.setMensaje("SE REGISTRO SOLICITUD EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO SOLICITUD EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar SOLICITUD EMPRESA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarSolicitudEmpresa/{id}")
    public ResponseEntity<RespuestaGenerica> EditarSolicitudEmpresa(@RequestBody SolicitudEmpresa solicitudEmpresaEnviada,@PathVariable Long id){
        List<SolicitudEmpresa> data = new ArrayList<>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            SolicitudEmpresa soli = solicitudEmpRepository.findById(id)
                    .map(res ->{
                        res.setFechaInicio(solicitudEmpresaEnviada.getFechaInicio());
                        res.setNumeroAlumnos(solicitudEmpresaEnviada.getNumeroAlumnos());
                        res.setEstado(solicitudEmpresaEnviada.isEstado());
                        res.setPdfSolicitud(solicitudEmpresaEnviada.getPdfSolicitud());
                        res.setRespuesta(solicitudEmpresaEnviada.getRespuesta());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO SolicitudEmpresa CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA PERSONA MODIFICADA
                        return solicitudEmpRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO SolicitudEmpresa CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new SolicitudEmpresa();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR SolicitudEmpresa, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarPersona/{id}")
    public ResponseEntity EliminarSolicitudEmpresa (@PathVariable Long id ){
        List<SolicitudEmpresa> data = new ArrayList<SolicitudEmpresa>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            solicitudEmpRepository.deleteById(id);
            if(solicitudEmpRepository!=null){
                data.add(new SolicitudEmpresa());
                respuesta.setMensaje("SE ELIMINO SolicitudEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO SolicitudEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR SolicitudEmpresa, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
