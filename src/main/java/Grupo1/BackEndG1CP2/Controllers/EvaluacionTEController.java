package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Repositories.EvaluacionA12Repository;
import Grupo1.BackEndG1CP2.Repositories.EvaluacionTERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EvaluacionTE")
public class EvaluacionTEController {

    @Autowired
    private EvaluacionTERepository evaluacionTERepository;


}