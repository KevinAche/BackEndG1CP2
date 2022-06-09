package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import Grupo1.BackEndG1CP2.Repositories.TutorEmpresarialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionTutorEmpresa")
public class TutorEmpresaController {

    @Autowired
    private TutorEmpresarialRepository tutorEmpresarialRepository;
}
