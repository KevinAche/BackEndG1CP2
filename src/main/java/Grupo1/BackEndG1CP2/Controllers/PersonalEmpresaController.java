package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Empresa;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaAlumSolicitudes;
import Grupo1.BackEndG1CP2.Models.Views.VistaEmpleadosEmpresa;
import Grupo1.BackEndG1CP2.Models.Views.VistaTutoresEmpresa;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarPersonal;
import Grupo1.BackEndG1CP2.Repositories.EmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListaEmpleadosEmpRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListaTutoresEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarPersonalRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import Grupo1.BackEndG1CP2.security.controller.AuthController;
import Grupo1.BackEndG1CP2.security.dto.NuevoUsuario;
import Grupo1.BackEndG1CP2.security.entity.Rol;
import Grupo1.BackEndG1CP2.security.entity.Usuario;
import Grupo1.BackEndG1CP2.security.enums.RolNombre;
import Grupo1.BackEndG1CP2.security.jwt.JwtProvider;
import Grupo1.BackEndG1CP2.security.services.RolService;
import Grupo1.BackEndG1CP2.security.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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

    @Autowired
    private AuthController authController;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public RolService rolService;

    @Autowired
    public JwtProvider jwtProvider;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private ListaTutoresEmpresaRepository listaTutoresEmpresaRepository;

    @Autowired
    private ListaEmpleadosEmpRepository listaEmpleadosEmpRepository;
    
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

    @GetMapping("/ListaPersonalEmp/{cedula}")
    public ResponseEntity<RespuestaGenerica> ListarPersonalEmp(@PathVariable String cedula){
        List<VistaListarPersonal> data = new ArrayList<>();
        RespuestaGenerica<VistaListarPersonal> respuesta = new RespuestaGenerica<>();
        try{
            List<VistaListarPersonal> empleados= listarPersonalRepository.findAll();
            List<VistaListarPersonal> lista = new ArrayList<>();
            for (VistaListarPersonal vis: empleados){
               if (vis.getCedula().equals(cedula)){
                   lista.add(vis);
               }
            }
            data=lista;
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

    @GetMapping("/ListaTutoresEmpresa/{id_empresa}")
    public ResponseEntity<RespuestaGenerica> ListarTutoresEmpresa(@PathVariable Long id_empresa){
        List<VistaTutoresEmpresa> data = new ArrayList<>();
        RespuestaGenerica<VistaTutoresEmpresa> respuesta = new RespuestaGenerica<>();
        try{
            List<VistaTutoresEmpresa> vista = listaTutoresEmpresaRepository.findAll();
            List<VistaTutoresEmpresa> lista = new ArrayList<>();

            for (VistaTutoresEmpresa vis: vista) {
                if(vis.getId_empresa()==id_empresa){
                    lista.add(vis);
                }
            }

            data= lista;
            respuesta.setMensaje("Se generó LISTADO DE TUTORES DE EMPRESA EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO DE TUTORES DE EMPRESA, causa ->"+e.getCause()+" || message->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/ListaPersonalEmpresa/{id_empresa}")
    public ResponseEntity<RespuestaGenerica> ListarPersonalEmpresa(@PathVariable Long id_empresa){
        List<VistaEmpleadosEmpresa> data = new ArrayList<>();
        RespuestaGenerica<VistaEmpleadosEmpresa> respuesta = new RespuestaGenerica<>();
        try{
            List<VistaEmpleadosEmpresa> vista = listaEmpleadosEmpRepository.findAll();
            List<VistaEmpleadosEmpresa> lista = new ArrayList<>();

            for (VistaEmpleadosEmpresa vis: vista) {
                if (vis.getId_empresa()==id_empresa){
                    lista.add(vis);
                }
            }
            data= lista;
            respuesta.setMensaje("Se generó LISTADO DE EMPLEADOS DE EMPRESA EXITOXAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO DE EMPLEADOS DE EMPRESA, causa ->"+e.getCause()+" || message->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/ListaGerente/{id}")
    public ResponseEntity<RespuestaGenerica> ListarGerente(@PathVariable Long id){

        List<PersonalEmpresa> data = new ArrayList<>();
        RespuestaGenerica<PersonalEmpresa> respuesta = new RespuestaGenerica<>();
        try{
            Empresa empresa = empresaRepository.findById(id).get();
            List<PersonalEmpresa> personalGeneral= personalEmpresaRepository.findByEmpresa(empresa);
            for (PersonalEmpresa per: personalGeneral){
                if(per.getCargo().equalsIgnoreCase("gerente")){
                    data.add(per);
                }
            }
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
                if(empresa!= null){
                    personalEnviado.setEmpresa(empresa.get());
                    personalEnviado.setPersona(persona);
                    PersonalEmpresa personal = personalEmpresaRepository.save(personalEnviado);
                    Set<String> roles = new HashSet<>();
                    roles.add("empleado");
                    NuevoUsuario nuevoUsuario = new NuevoUsuario();
                    nuevoUsuario.setPersona(persona);
                    nuevoUsuario.setRoles(roles);
                    System.out.println(roles);
                    if(authController.creacionUsuarios(nuevoUsuario)){
                        System.out.println("USUARIO EMPLEADO CREADO");
                    }else{
                        System.out.println("ERROR AL CREAR USUARIO");
                    }
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

    @PutMapping("/EditarPersonal/{cedula}")
    public ResponseEntity<RespuestaGenerica> EditarPersonal(@RequestBody PersonalEmpresa personalEnviado,@PathVariable String cedula){
        List<PersonalEmpresa> data = new ArrayList<>();
        RespuestaGenerica<PersonalEmpresa> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){
                List<PersonalEmpresa> listaPersonal = personalEmpresaRepository.findAll();
                for (PersonalEmpresa per: listaPersonal) {
                    if(per.getPersona().getCedula()==persona.getCedula()){
                        personalEnviado.setEmpresa(per.getEmpresa());
                        personalEnviado.setPersona(per.getPersona());
                        personalEnviado.setIdPersonal(per.getIdPersonal());
                    }
                }
                PersonalEmpresa personal = personalEmpresaRepository.findById(personalEnviado.getIdPersonal()).map(res ->{
                    res.setCargo(personalEnviado.getCargo());
                    res.setEmpresa(personalEnviado.getEmpresa());
                    res.setPersona(personalEnviado.getPersona());
                    data.add(res);
                    respuesta.setMensaje("SE MODIFICO EMPLEADO CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                    return personalEmpresaRepository.save(res);
                }).orElseGet(()->{
                    respuesta.setMensaje("NO SE MODIFICO EL EMPLEADO");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                    return new PersonalEmpresa();
                });
            }else{
                respuesta.setMensaje("EL EMPLEADO NO PUDO SER MODIFICADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado.set(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR EL EMPLEADO, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }

    @DeleteMapping("/EliminarPersonal/{cedula}")
    public ResponseEntity<RespuestaGenerica> EliminarPersonal(@PathVariable String cedula){
        List<PersonalEmpresa> data = new ArrayList<PersonalEmpresa>();
        RespuestaGenerica<PersonalEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){
                List<PersonalEmpresa> listaPersonal = personalEmpresaRepository.findAll();
                PersonalEmpresa personal = new PersonalEmpresa();
                for (PersonalEmpresa per: listaPersonal) {
                    if(persona.getCedula()==per.getPersona().getCedula()){
                        personal=per;
                    }
                }
                if(personal.getPersona().getCedula().equals(cedula)){
                     personalEmpresaRepository.deleteById(personal.getIdPersonal());
                     personaRepository.deleteById(persona.getIdPersona());
                    data.add(new PersonalEmpresa());
                    respuesta.setMensaje("SE ELIMINO EL EMPLEADO CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);

                }else{
                    data.add(null);
                    respuesta.setMensaje("EL EMPLEADO NO PUDO SER ELIMINADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA ALUM");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                data.add(null);
                respuesta.setMensaje("EL EMPLEADO NO PUDO SER ELIMINADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR EL EMPLEADO, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }


    @GetMapping("/DevolverEmpresa/{cedula}")
    public ResponseEntity<RespuestaGenerica> BuscarEmpresaEmpleado(@PathVariable String cedula){
        List<Empresa> data = new ArrayList<>();
        RespuestaGenerica<Empresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){

                PersonalEmpresa personalEmpresa = personalEmpresaRepository.findByPersona(persona);
                if(personalEmpresa!= null){
                    Empresa empresa = empresaRepository.findById(personalEmpresa.getEmpresa().getIdEmpresa()).get();
                    data.add(empresa);
                    respuesta.setMensaje("SE ENCONTRO EMPRESA DE EMPLEADO ");
                    respuesta.setData(data);
                    respuesta.setEstado(0);

                }else{
                    data.add(null);
                    respuesta.setMensaje("NO SE ENCONTRO PERSONAL DEL EMPLEADO");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                data.add(null);
                respuesta.setMensaje("EL EMPLEADO NO PUDO SER ELIMINADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR EL EMPLEADO, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }

    public boolean creacionUsuarios(NuevoUsuario nuevoUsuario){
        System.out.println("ENTRO AL METODO");
        if (usuarioService.existsByUsername(nuevoUsuario.getUsername()))
            return false;
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return false;
        Persona persona = personaRepository.findById(nuevoUsuario.getPersona().getIdPersona()).get();
        System.out.println("persona obtenida: "+persona.getCedula());
        Usuario usuario = new Usuario(
                persona.getPrimerNombre() + " " + persona.getPrimerApellido(),
                persona.getCedula(), persona.getCorreo(),
                passwordEncoder.encode(persona.getCedula()), nuevoUsuario.getPersona());
        System.out.println("usuario generado");
        Set<Rol> roles = new HashSet<>();
        try {
            if (nuevoUsuario.getRoles().contains("estudiante")){
                System.out.println(rolService.getByUsername(RolNombre.ROLE_ESTUDIANTE).get());
                roles.add(rolService.getByUsername(RolNombre.ROLE_ESTUDIANTE).get());
            }
        } catch (Exception ex){
            System.out.println(ex);
        }

        if (nuevoUsuario.getRoles().contains("admin")){
            roles.add(rolService.getByUsername(RolNombre.ROLE_ADMIN).get());}
        if (nuevoUsuario.getRoles().contains("docente")){
            roles.add(rolService.getByUsername(RolNombre.ROLE_DOCENTE).get());}
        if (nuevoUsuario.getRoles().contains("responsable")){
            roles.add(rolService.getByUsername(RolNombre.ROLE_RESPONSABLEPPP).get());}
        if (nuevoUsuario.getRoles().contains("tacademico")){
            roles.add(rolService.getByUsername(RolNombre.ROLE_TUTORACADEMICO).get());}
        if (nuevoUsuario.getRoles().contains("tempresarial")){
            roles.add(rolService.getByUsername(RolNombre.ROLE_TUTOREMPRESARIAL).get());}
        System.out.println("NO COINCIDIO NINGUN ROL");
        if (nuevoUsuario.getRoles().contains("empleado")){
            System.out.println("ROL EMPLEADO SELECCIONADO");
            roles.add(rolService.getByUsername(RolNombre.ROLE_EMPLEADO).get());

        }
        System.out.println("ROL ASIGNADO");
        usuario.setRoles(roles);
        System.out.println("TERMINA EL METODO");
        usuarioService.save(usuario);
        System.out.println("USUARIO CREADO");
        return true;
    }
    
}
