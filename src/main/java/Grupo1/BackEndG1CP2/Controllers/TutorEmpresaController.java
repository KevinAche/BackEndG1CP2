package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.TutorEmpresarialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionTutorEmpresa")
public class TutorEmpresaController {

    @Autowired
    private TutorEmpresarialRepository tutorEmpresarialRepository;
}
