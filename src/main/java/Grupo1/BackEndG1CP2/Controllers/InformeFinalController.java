package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Actividades;
import Grupo1.BackEndG1CP2.Models.InformeFinal;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.InformeFinalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionInformeFinal")
public class InformeFinalController {
    @Autowired
    private InformeFinalRepository informeFinalRepository;

    @GetMapping("/ListaInformeFinal")
    public ResponseEntity<RespuestaGenerica> ListarInformeFinal(){
        List<InformeFinal> data = new ArrayList<>();
        RespuestaGenerica<InformeFinal> respuesta = new RespuestaGenerica<>();
        try {
            data=informeFinalRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO InformeFinal");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO InformeFinal, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearInformeFinal")
    public ResponseEntity<RespuestaGenerica> CrearInformeFinal(@RequestBody InformeFinal informeFinalEnviado){
        List<InformeFinal> data = new ArrayList<>();
        RespuestaGenerica<InformeFinal> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {

            InformeFinal informeFinal = informeFinalRepository.save(informeFinalEnviado);
            data.add(informeFinal);
            if(informeFinal !=null){
                respuesta.setMensaje("SE REGISTRO InformeFinal CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO InformeFinal CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar InformeFinal, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarInformeFinal/{id}")
    public ResponseEntity<RespuestaGenerica> EditarInformeFinal(@RequestBody InformeFinal informeFinalEnviado,@PathVariable Long id){
        List<InformeFinal> data = new ArrayList<>();
        RespuestaGenerica<InformeFinal> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            InformeFinal inf = informeFinalRepository.findById(id)
                    .map(res ->{
                        res.setFechaEmision(informeFinalEnviado.getFechaEmision());
                        res.setDocInformeFinal(informeFinalEnviado.getDocInformeFinal());
                        res.setAlumno(informeFinalEnviado.getAlumno());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO InformeFinal CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA Actividades MODIFICADA
                        return informeFinalRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO InformeFinal CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new InformeFinal();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR InformeFinal, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarInformeFinal/{id}")
    public ResponseEntity EliminarInformeFinal (@PathVariable Long id ){
        List<InformeFinal> data = new ArrayList<InformeFinal>();
        RespuestaGenerica<InformeFinal> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {

            informeFinalRepository.deleteById(id);
            if(informeFinalRepository!=null){
                data.add(new InformeFinal());
                respuesta.setMensaje("SE ELIMINO InformeFinal CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO InformeFinal CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR InformeFinal, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }
}
