package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.Registro_VisitaEmpresa;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaLista_Responsables;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarDocentes;
import Grupo1.BackEndG1CP2.Repositories.ResponsablePPPRepository;
import Grupo1.BackEndG1CP2.Repositories.SolicitudEmpRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarResponsablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionResponsablePPP")
public class ResponsablePPPController {

    @Autowired
    private ResponsablePPPRepository responsablePPPRepository;

    @Autowired
    private ListarResponsablesRepository listarResponsablesRepository;

    @GetMapping("/ListarResponsablesVista")
    public ResponseEntity<RespuestaGenerica> ListarRegistro_VisitaEmpresa(){
        List<VistaLista_Responsables> data = new ArrayList<>();
        RespuestaGenerica<VistaLista_Responsables> respuesta = new RespuestaGenerica<>();
        try {
            data=listarResponsablesRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO RESPONSABLES EXITOSAMENTE");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO RESPONSABLES, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/ObtenerResponsable/{cedula}")
    public ResponseEntity<RespuestaGenerica> DeolverResponsable(@PathVariable String cedula){
        List<VistaLista_Responsables> data = new ArrayList<>();
        RespuestaGenerica<VistaLista_Responsables> respuesta = new RespuestaGenerica<>();
        try {
            VistaLista_Responsables vistaLista_responsables =listarResponsablesRepository.findByCedula(cedula);
            if(vistaLista_responsables!=null){
                data.add(vistaLista_responsables);
                respuesta.setMensaje("Se obtuvo  RESPONSABLE EXITOSAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("Hubo un problema al obtener RESPONSABLE debido a que la cedula -> "+cedula+" no esta designada como responsable");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO RESPONSABLES, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }
}
