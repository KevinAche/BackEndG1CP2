package Grupo1.BackEndG1CP2.Controllers;


import Grupo1.BackEndG1CP2.Repositories.CertificadoEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/GestionCertificadoEmpresa")
public class CertificadoEmpresa {
    //Certificado A12.1

    @Autowired
    private CertificadoEmpresaRepository certificadoEmpresaRepository;

    //


}
