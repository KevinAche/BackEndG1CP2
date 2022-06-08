package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Repositories.CertificadoA12Repository;
import Grupo1.BackEndG1CP2.Repositories.EvaluacionA12Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EvaluacionA12")
public class EvaluacionA12Controller {

    @Autowired
    private EvaluacionA12Repository evaluacionA12Repository;
}
