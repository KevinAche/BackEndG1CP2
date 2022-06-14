package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.SolicitudAlumno;
import Grupo1.BackEndG1CP2.Models.SolicitudEmpresa;
import Grupo1.BackEndG1CP2.Repositories.SolicitudAlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
