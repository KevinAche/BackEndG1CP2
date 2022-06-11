package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarDocentes;
import Grupo1.BackEndG1CP2.Repositories.ResponsablePPPRepository;
import Grupo1.BackEndG1CP2.Repositories.SolicitudEmpRepository;
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

}
