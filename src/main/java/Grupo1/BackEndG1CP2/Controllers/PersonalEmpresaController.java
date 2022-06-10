package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Carrera;
import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.Empresa;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarDocentes;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarPersonal;
import Grupo1.BackEndG1CP2.Repositories.EmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarPersonalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionPersonalEmpresa")
public class PersonalEmpresaController {

    @Autowired
    private PersonalEmpresaRepository personalEmpresaRepository;
    
    @Autowired
    private ListarPersonalRepository listarPersonalRepository;
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private EmpresaRepository empresaRepository;
    
    @GetMapping("/ListaPersonal")
    public ResponseEntity<RespuestaGenerica> ListarPersonal(){
       List<VistaListarPersonal> data = new ArrayList<>();
       RespuestaGenerica<VistaListarPersonal> respuesta = new RespuestaGenerica<>();
       try{
           data= listarPersonalRepository.findAll();
           respuesta.setMensaje("Se generó LISTADO DE PERSONAL EXITOXAMENTE");
           respuesta.setData(data);
           respuesta.setEstado(0);
       }catch (Exception e){
           respuesta.setMensaje("Hubo un problema al generar LISTADO DE PERSONAL, causa ->"+e.getCause()+" || message->"+e.getMessage());
           respuesta.setData(data);
           respuesta.setEstado(1);
       }
       return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }



    @PostMapping("/CrearPersonal/{cedula}/{id_empresa}")
    public ResponseEntity<RespuestaGenerica> CrearPersonalE(@RequestBody PersonalEmpresa personalEnviado,@PathVariable String cedula,@PathVariable Long id_empresa){
        List<PersonalEmpresa> data = new ArrayList<>();
        RespuestaGenerica<PersonalEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona != null){
                Optional<Empresa> empresa = empresaRepository.findById(id_empresa);
                if(!empresa.isEmpty()){
                    personalEnviado.setEmpresa(empresa.get());
                    personalEnviado.setPersona(persona);
                    PersonalEmpresa personal = personalEmpresaRepository.save(personalEnviado);
                    data.add(personal);
                    if(personal !=null){
                        respuesta.setMensaje("SE REGISTRO EMPLEADO CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                    }else{
                        respuesta.setMensaje("NO SE REGISTRO EMPLEADO CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado= HttpStatus.BAD_REQUEST;
                    }
                }else{
                    respuesta.setMensaje("NO SE REGISTRO EMPLEADO CORRECTAMENTE DEBIDO A QUE EL ID DE EMPRESA ENVIADO NO SE ENCONTRO EN LA BASE");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                respuesta.setMensaje("NO SE REGISTRO EMPLEADO CORRECTAMENTE DEBIDO A QUE EL ID DE PERSONA ENVIADO NO SE ENCONTRO EN LA BASE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar EMPLEADO, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }
    
    
}
