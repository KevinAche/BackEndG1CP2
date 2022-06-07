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

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/ListaAlumnos")
    public List<Alumno> ListarAlumnos(){
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos;
    }

    @PostMapping("/CrearAlumno")
    public ResponseEntity<RespuestaGenerica> CrearNuevoAlumno(@RequestBody Persona persona ,@RequestBody Alumno alumno){

        List<Alumno> data = new ArrayList<>();
        RespuestaGenerica<Alumno> respuesta = new RespuestaGenerica<>();

        try{

            Persona persona1 =  personaRepository.save(persona);
            if(persona1!=null){
                respuesta.setMensaje("SE INSERTO PERSONA: "+persona1.toString());
                respuesta.setData(data);
                respuesta.setEstado(0);
                if (!personaRepository.findById(persona.getIdPersona()).isEmpty()){
                    alumno.setPersona(persona1);
                    Alumno alumno1 = alumnoRepository.save(alumno);
                    if(alumno1!=null){
                        respuesta.setMensaje("SE INSERTO PERSONA:"+alumno1.toString());
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                    }
                }
            }else{
                respuesta.setMensaje("Hubo un problema al insertar PERSONA");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        }catch (Exception ex){
            respuesta.setMensaje("Hubo un problema al insertar el nuevo alumno");
            respuesta.setData(data);
            respuesta.setEstado(1);
            System.out.println("No se pudo almacenar el objeto en la base de datos");
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.CREATED);
    }


}
