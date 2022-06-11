package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Registro_Asistencias;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarAlumnos;
import Grupo1.BackEndG1CP2.Repositories.ActividadesDiariasRepository;
import Grupo1.BackEndG1CP2.Repositories.AlumnoRepository;
import Grupo1.BackEndG1CP2.Repositories.RegistroAsistenciaRepository;
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
@RequestMapping("/RegistroAsistencias")
public class RegistroAsistenciasController {


    @Autowired
    private ActividadesDiariasRepository actividadesDiariasRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private RegistroAsistenciaRepository registroAsistenciaRepository;




    @GetMapping("/ListaRegAsistencias")
    public ResponseEntity<RespuestaGenerica> ListarRegAsistencias(){
        List<Registro_Asistencias> data = new ArrayList<>();
        RespuestaGenerica<Registro_Asistencias> respuesta = new RespuestaGenerica<>();
        try{
            data= registroAsistenciaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO REGISTRO DE ASISTENCIAS EXITOSAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO REGISTRO DE ASISTENCIAS, causa ->"+e.getCause()+" || message->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


}
