package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Empresa;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.ActividadesRepository;
import Grupo1.BackEndG1CP2.Repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionEmpresa")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/ListaEmpresas")
    public ResponseEntity<RespuestaGenerica> ListarEmpresas(){
        List<Empresa> data = new ArrayList<>();
        RespuestaGenerica<Empresa> respuesta = new RespuestaGenerica<>();
        try {
            data=empresaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO EMPRESAS EXITOSAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO PERSONAS, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/CrearEmpresa")
    public ResponseEntity<RespuestaGenerica> CrearEmpresa(@RequestBody Empresa empresaEnviada){
        List<Empresa> data = new ArrayList<>();
        RespuestaGenerica<Empresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Empresa empresa = empresaRepository.save(empresaEnviada);
            data.add(empresa);
            if(empresa !=null){
                respuesta.setMensaje("SE REGISTRO EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar EMPRESA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }

    @PutMapping("/EditarEmpresa/{id}")
    public ResponseEntity<RespuestaGenerica> EditarEmpresa(@RequestBody Empresa empresaEnviada,@PathVariable Long id){
        List<Empresa> data = new ArrayList<>();
        RespuestaGenerica<Empresa> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado  = new AtomicReference<>(HttpStatus.OK);
        try {
            Empresa emp = empresaRepository.findById(id)
                    .map(res ->{
                        res.setDireccion(empresaEnviada.getDireccion());
                        res.setNombreEmpresa(empresaEnviada.getNombreEmpresa());
                        res.setMision(empresaEnviada.getMision());
                        res.setVision(empresaEnviada.getVision());
                        res.setTelefono(empresaEnviada.getTelefono());
                        res.setRuc(empresaEnviada.getRuc());
                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO EMPRESA CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA PERSONA MODIFICADA
                        return empresaRepository.save(res);
                    })
                    .orElseGet(()->{
                        respuesta.setMensaje("NO SE ENCONTRO EMPRESA CON EL ID INGRESADO: "+id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new Empresa();
                    });
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al MODIFICAR EMPRESA, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }

    @DeleteMapping("/EliminarEmpresa/{id}")
    public ResponseEntity EliminarEmpresa (@PathVariable Long id ){
        List<Empresa> data = new ArrayList<>();
        RespuestaGenerica<Empresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.OK;

        try {
            empresaRepository.deleteById(id);
            if(empresaRepository!=null){
                data.add(new Empresa());
                respuesta.setMensaje("SE ELIMINO EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                estado= HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado= HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR EMPRESA, causa->"+e.getCause()+ " ||  message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta,estado);
    }


}
