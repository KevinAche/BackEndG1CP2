package Grupo1.BackEndG1CP2.Controllers;


import Grupo1.BackEndG1CP2.Models.ActaDeReunion;
import Grupo1.BackEndG1CP2.Models.CertificadoEmpresa;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActaReunionRepository;
import Grupo1.BackEndG1CP2.Repositories.CertificadoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionActaDeReunion")
public class CertificadoEmpresaController {
    //Certificado A12.1
    @Autowired
    private CertificadoEmpresaRepository certificadoEmpresaRepository;

    @GetMapping("/ListaCertificadoEmpresa")
    public ResponseEntity<RespuestaGenerica> ListarCertificadoEmpresa(){
        List<CertificadoEmpresa> data = new ArrayList<>();
        RespuestaGenerica<CertificadoEmpresa> respuesta = new RespuestaGenerica<>();
        try {
            data=certificadoEmpresaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO CertificadoEmpresa EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO CertificadoEmpresa, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearCertificadoEmpresa")
    public ResponseEntity<RespuestaGenerica> CrearCertificadoEmpresa(@RequestBody CertificadoEmpresa certificadoEmpresaEnviado){
        List<CertificadoEmpresa> data = new ArrayList<>();
        RespuestaGenerica<CertificadoEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            CertificadoEmpresa certificadoEmpresa = certificadoEmpresaRepository.save(certificadoEmpresaEnviado);
            data.add(certificadoEmpresa);
            if(certificadoEmpresa !=null){
                respuesta.setMensaje("SE REGISTRO CertificadoEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO CertificadoEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar CertificadoEmpresa, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarCertificadoEmpresa/{id}")
    public ResponseEntity<RespuestaGenerica> EditarCertificadoEmpresa(@RequestBody CertificadoEmpresa certificadoEmpresaEnviado,@PathVariable Long id){
        List<CertificadoEmpresa> data = new ArrayList<>();
        RespuestaGenerica<CertificadoEmpresa> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            CertificadoEmpresa cert = certificadoEmpresaRepository.findById(id)
                    .map(res ->{
                        res.setFechaEmision(certificadoEmpresaEnviado.getFechaEmision());
                        res.setDocCertificadoE(certificadoEmpresaEnviado.getDocCertificadoE());
                        res.setTutorEmpresarial(certificadoEmpresaEnviado.getTutorEmpresarial());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO CertificadoEmpresa CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA CertificadoEmpresa MODIFICADA
                        return certificadoEmpresaRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO CertificadoEmpresa CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new CertificadoEmpresa();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR CertificadoEmpresa, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarCertificadoEmpresa/{id}")
    public ResponseEntity EliminarCertificadoEmpresa (@PathVariable Long id ){
        List<CertificadoEmpresa> data = new ArrayList<CertificadoEmpresa>();
        RespuestaGenerica<CertificadoEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            certificadoEmpresaRepository.deleteById(id);
            if(certificadoEmpresaRepository!=null){
                data.add(new CertificadoEmpresa());
                respuesta.setMensaje("SE ELIMINO CertificadoEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO CertificadoEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR CertificadoEmpresa, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }

}
