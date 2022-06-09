package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Carrera;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionCarrera")
public class CarreraController {

    @Autowired
    private CarreraRepository carreraRepository;

    @GetMapping("/ListarCarreras")
    public ResponseEntity<RespuestaGenerica> ListarCarreras(){
        List<Carrera> data = new ArrayList<>();
        RespuestaGenerica<Carrera> respuesta = new RespuestaGenerica<>();
        try {
            data= carreraRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO CARRERA EXITOSAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO CARREARA, causa ->"+e.getCause()+" || message->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return  new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearCarrera")
    public ResponseEntity<RespuestaGenerica> CrearCarrera(@RequestBody Carrera carreraEnviada){
        List<Carrera> data = new ArrayList<>();
        RespuestaGenerica<Carrera> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Carrera carrera = carreraRepository.save(carreraEnviada);
            data.add(carrera);
            if(carrera !=null){
                respuesta.setMensaje("SE REGISTRO CARRERA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO CARRERA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al inserta CARRERA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


    @PutMapping("/EditarCarrera/{id}")
    public ResponseEntity<RespuestaGenerica> EditarCarrera(@RequestBody Carrera carreraEnviada,@PathVariable Long id){
        List<Carrera> data = new ArrayList<>();
        RespuestaGenerica<Carrera> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Carrera car = carreraRepository.findById(id)
                    .map(res ->{
                        res.setAbreviatura(carreraEnviada.getAbreviatura());
                        res.setModalidad(carreraEnviada.getModalidad());
                        res.setNombre(carreraEnviada.getNombre());
                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO CARRERA CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA PERSONA MODIFICADA
                        return carreraRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO CARRERA CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Carrera();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR CARRERA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarCarrera/{id}")
    public ResponseEntity EliminarCarrera (@PathVariable Long id ){
        List<Carrera> data = new ArrayList<Carrera>();
        RespuestaGenerica<Carrera> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;
        try {

            carreraRepository.deleteById(id);
            if(carreraRepository!=null){
                data.add(new Carrera());
                respuesta.setMensaje("SE ELIMINO CARRERA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO CARRERA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR CARRERA, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }


}
