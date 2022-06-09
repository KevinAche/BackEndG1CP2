package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Repositories.EmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.SolicitudEmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionSolicitudEmpresa")
public class SolicitudEmpresaController {

    @Autowired
    private SolicitudEmpRepository solicitudEmpRepository;
}
