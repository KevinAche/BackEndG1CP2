package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Repositories.PersonalEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PersonalEmpresa")
public class PersonalEmpresaController {

    @Autowired
    private PersonalEmpresaRepository personalEmpresaRepository;
}
