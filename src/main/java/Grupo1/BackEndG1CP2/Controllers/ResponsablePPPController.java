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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
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
}
