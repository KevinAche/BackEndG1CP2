package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/GestionPersona")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/ListaPersonas")
    public ResponseEntity<RespuestaGenerica> ListarEstudiantes(){
        List<Persona> data = new ArrayList<>();
        RespuestaGenerica<Persona> respuesta = new RespuestaGenerica<>();
        try {
            data=personaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO PERSONAS EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);

        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO PERSONAS, causa ->"+e.getCause());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearPersona")
    public ResponseEntity<RespuestaGenerica> CrearPersona(@RequestBody Persona personaEnviada){
        List<Persona> data = new ArrayList<>();
        RespuestaGenerica<Persona> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.save(personaEnviada);
            data.add(persona);
            if(persona !=null){
                respuesta.setMensaje("SE REGISTRO PERSONA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO PERSONA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO PERSONAS, causa ->"+e.getCause());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarPersona/{id}")
    public ResponseEntity<RespuestaGenerica> EditarPersona(@RequestBody Persona personaEnviada,@PathVariable Long id){
        List<Persona> data = new ArrayList<>();
        RespuestaGenerica<Persona> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona per = personaRepository.findById(id)
                    .map(res ->{
                        res.setCedula(personaEnviada.getCedula());
                        res.setCorreo(personaEnviada.getCorreo());
                        res.setDireccion(personaEnviada.getDireccion());
                        res.setTelefono(personaEnviada.getTelefono());
                        res.setFechaNac(personaEnviada.getFechaNac());
                        res.setSegundoApellido(personaEnviada.getSegundoApellido());
                        res.setSegundoNombre(personaEnviada.getSegundoNombre());
                        res.setPrimerNombre(personaEnviada.getPrimerNombre());
                        res.setPrimerApellido(personaEnviada.getPrimerApellido());
                        return personaRepository.save(res);
                    })
                    .orElseGet(()->{
                        return new Persona();
                    });
            data.add(per);
            if(per !=null){
                respuesta.setMensaje("SE MODIFICO PERSONA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE MODIFICO PERSONA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR PERSONA, causa ->"+e.getCause());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }
}
