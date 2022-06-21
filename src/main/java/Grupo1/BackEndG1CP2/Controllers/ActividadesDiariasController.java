package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.Actividades_Diarias;
import Grupo1.BackEndG1CP2.Models.Carrera;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesDiariasRepository;
import Grupo1.BackEndG1CP2.Repositories.RegistroAsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/ActividadesDiarias")
public class ActividadesDiariasController {

    @Autowired
    private ActividadesDiariasRepository actividadesDiariasRepository;

    @Autowired
    private RegistroAsistenciaRepository registroAsistenciaRepository;

    @GetMapping("/ListaActividadesDiarias")
    public ResponseEntity<RespuestaGenerica> ListarActividadesDiarias(){
        List<Actividades_Diarias> data = new ArrayList<>();
        RespuestaGenerica<Actividades_Diarias> respuesta = new RespuestaGenerica<>();
        try {
            data=actividadesDiariasRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO Actividades DIARIAS");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO Actividades DIARIAS, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/CrearActividadesDiarias")
    public ResponseEntity<RespuestaGenerica> CrearActividades(@RequestBody Actividades_Diarias actividadesDiariasEnviado){
        List<Actividades_Diarias> data = new ArrayList<>();
        RespuestaGenerica<Actividades_Diarias> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            Actividades_Diarias activiDiarias = actividadesDiariasRepository.save(actividadesDiariasEnviado);
            data.add(activiDiarias);
            if(activiDiarias !=null){
                respuesta.setMensaje("SE REGISTRO Actividades DIARIAS CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO Actividades DIARIAS CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar Actividades, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }



    @DeleteMapping("/EliminarActividadDiaria/{id}")
    public ResponseEntity EliminarActividad (@PathVariable Long id ){
        List<Actividades_Diarias> data = new ArrayList<>();
        RespuestaGenerica<Actividades_Diarias> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {
            actividadesDiariasRepository.deleteById(id);
            if(actividadesDiariasRepository!=null){
                data.add(new Actividades_Diarias());
                respuesta.setMensaje("SE ELIMINO ACTIVIDAD DIARIA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO ACTIVIDAD DIARIA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR ACTIVIDAD DIARIA, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
