package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.AlumnoRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/GestionAlumno")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;


    @GetMapping("/ListaAlumnos")
    public ResponseEntity<RespuestaGenerica> ListarAlumnos(){
       List<Alumno> data = new ArrayList<>();
       RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();
       try{
            data= alumnoRepository.findAll();
           respuesta.setMensaje("Se genero LISTADO PERSONAS EXITOXAMENTE");
           respuesta.setData(data);
           respuesta.setEstado(0);
       }catch (Exception e){

       }

       return null;
    }



}
