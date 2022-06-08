package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.CertificadoA12_1;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.CertificadoA12Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CertificadoA12")
public class CertificadoA12_1Controller {

    @Autowired
    private CertificadoA12Repository certificadoA12Repository;

    @GetMapping("/ListaCertificados")
    public ResponseEntity<RespuestaGenerica> ListarCertificados(){
        List<CertificadoA12_1> data = new ArrayList<>();
        RespuestaGenerica<CertificadoA12_1> respuesta = new RespuestaGenerica<>();
        try {
            data=certificadoA12Repository.findAll();
            respuesta.setMensaje("Se genero LISTADO CERTIFICADO EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);

        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO PERSONAS, causa ->"+e.getCause());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/CrearCertificado")
    public ResponseEntity<RespuestaGenerica> CrearCertificado(@RequestBody CertificadoA12_1 certificadoA12_1){
        List<CertificadoA12_1> data = new ArrayList<>();
        RespuestaGenerica<CertificadoA12_1> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            CertificadoA12_1 certificadoA121 = certificadoA12Repository.save(certificadoA12_1);
            data.add(certificadoA121);
            if(certificadoA121 !=null){
                respuesta.setMensaje("SE REGISTRO CERTIFICADO CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO CERTIFICADO CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO CERTIFICADO, causa ->"+e.getCause());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


}
