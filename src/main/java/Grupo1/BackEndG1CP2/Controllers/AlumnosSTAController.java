package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaAlumnosSinTutorAcad;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarAlumnos;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarVistaAlumnosSTARepository;
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
@RequestMapping("/GestionAlumnoSTA")
public class AlumnosSTAController {

    @Autowired
    private ListarVistaAlumnosSTARepository listarVistaAlumnosSTARepository;

    @GetMapping("/ListaAlumnos")
    public ResponseEntity<RespuestaGenerica> ListarAlumnos(){
        List<VistaAlumnosSinTutorAcad> data = new ArrayList<>();
        RespuestaGenerica<VistaAlumnosSinTutorAcad> respuesta = new RespuestaGenerica<>();
        try{
            data= listarVistaAlumnosSTARepository.findAll();
            respuesta.setMensaje("Se genero LISTADO ALUMNOS EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO ALUMNOS, causa ->"+e.getCause()+" || message->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


}
