package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Carrera;
import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarDocentes;
import Grupo1.BackEndG1CP2.Repositories.CarreraRepository;
import Grupo1.BackEndG1CP2.Repositories.DocenteRepository;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarAlumnosRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarDocentesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionDocente")
public class DocenteController {

    @Autowired
    private DocenteRepository docenteRepository;
    
    @Autowired
    private ListarDocentesRepository listarDocentesRepository;
    
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private CarreraRepository carreraRepository;
    

    @GetMapping("/ListaDocentes")
    public ResponseEntity<RespuestaGenerica> ListarDocentes(){
       List<VistaListarDocentes> data = new ArrayList<>();
       RespuestaGenerica<VistaListarDocentes> respuesta = new RespuestaGenerica<>();
       try{
           data= listarDocentesRepository.findAll();
           respuesta.setMensaje("Se generó LISTADO DOCENTES EXITOXAMENTE");
           respuesta.setData(data);
           respuesta.setEstado(0);
       }catch (Exception e){
           respuesta.setMensaje("Hubo un problema al generar LISTADO DOCENTES, causa ->"+e.getCause()+" || message->"+e.getMessage());
           respuesta.setData(data);
           respuesta.setEstado(1);
       }
       return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }
    
    @PostMapping("/CrearDocente/{cedula}/{id_carrera}")
    public ResponseEntity<RespuestaGenerica> CrearDocente(@RequestBody Docente docenteEnviado,@PathVariable String cedula,@PathVariable Long id_carrera){
        List<Docente> data = new ArrayList<>();
        RespuestaGenerica<Docente> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona != null){
                Optional<Carrera> carrera = carreraRepository.findById(id_carrera);
                if(!carrera.isEmpty()){
                    docenteEnviado.setCarrera(carrera.get());
                    docenteEnviado.setPersona(persona);
                    Docente docente = docenteRepository.save(docenteEnviado);
                    data.add(docente);
                    if(docente !=null){
                        respuesta.setMensaje("SE REGISTRO DOCENTE CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                    }else{
                        respuesta.setMensaje("NO SE REGISTRO DOCENTE CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado= HttpStatus.BAD_REQUEST;
                    }
                }else{
                    respuesta.setMensaje("NO SE REGISTRO DOCENTE CORRECTAMENTE DEBIDO A QUE EL ID DE CARRERA ENVIADO NO SE ENCONTRO EN LA BASE");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                respuesta.setMensaje("NO SE REGISTRO DOCENTE CORRECTAMENTE DEBIDO A QUE EL ID DE PERSONA ENVIADO NO SE ENCONTRO EN LA BASE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar DOCENTE, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }
    
    @PutMapping("/EditarDocente/{cedula}")
    public ResponseEntity<RespuestaGenerica> EditarDocente(@RequestBody Docente docenteEnviado,@PathVariable String cedula){
        List<Docente> data = new ArrayList<>();
        RespuestaGenerica<Docente> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){
                List<Docente> listaDocentes = docenteRepository.findAll();
                for (Docente doc: listaDocentes) {
                    if(doc.getPersona().getCedula()==persona.getCedula()){
                        docenteEnviado.setCarrera(doc.getCarrera());
                        docenteEnviado.setPersona(doc.getPersona());
                        docenteEnviado.setIdDocente(doc.getIdDocente());
                    }
                }
                Docente docente = docenteRepository.findById(docenteEnviado.getIdDocente()).map(res ->{
                    res.setTitulo(docenteEnviado.getTitulo());
                    res.setArea(docenteEnviado.getArea());
                    res.setAbrevTitulo(docenteEnviado.getAbrevTitulo());
                    res.setCarrera(docenteEnviado.getCarrera());
                    res.setPersona(docenteEnviado.getPersona());
                    data.add(res);
                    respuesta.setMensaje("SE MODIFICO DOCENTE CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                    return docenteRepository.save(res);
                }).orElseGet(()->{
                    respuesta.setMensaje("NO SE MODIFICO EL DOCENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                    return new Docente();
                });
            }else{
                respuesta.setMensaje("EL DOCENTE NO PUDO SER MODIFICADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado.set(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR DOCENTE, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }
    
    @DeleteMapping("/EliminarDocente/{cedula}")
    public ResponseEntity<RespuestaGenerica> EliminarDocente(@PathVariable String cedula){
        List<Docente> data = new ArrayList<Docente>();
        RespuestaGenerica<Docente> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            if(persona!=null){
                List<Docente> listaDocentes = docenteRepository.findAll();
                Docente docente = new Docente();
                for (Docente doc: listaDocentes) {
                    if(persona.getCedula()==doc.getPersona().getCedula()){
                        docente=doc;
                    }
                }
                if(docente.getPersona().getCedula().equals(cedula)){
                     docenteRepository.deleteById(docente.getIdDocente());
                     personaRepository.deleteById(persona.getIdPersona());
                    data.add(new Docente());
                    respuesta.setMensaje("SE ELIMINO DOCENTE CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);

                }else{
                    data.add(null);
                    respuesta.setMensaje("EL DOCENTE NO PUDO SER ELIMINADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA ALUM");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado= HttpStatus.BAD_REQUEST;
                }
            }else{
                data.add(null);
                respuesta.setMensaje("EL DOCENTE NO PUDO SER ELIMINADO DEBIDO A QUE LA CEDULA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR DOCENTE, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
