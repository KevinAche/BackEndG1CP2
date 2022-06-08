package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Repositories.ResponsablePPPRepository;
import Grupo1.BackEndG1CP2.Repositories.SolicitudEmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ResponsablePPP")
public class ResponsablePPPController {

    @Autowired
    private ResponsablePPPRepository responsablePPPRepository;
}
