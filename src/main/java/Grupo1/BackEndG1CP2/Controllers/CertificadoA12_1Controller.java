package Grupo1.BackEndG1CP2.Controllers;


import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Repositories.CertificadoA12Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CertificadoA12")
public class CertificadoA12_1Controller {

    @Autowired
    private CertificadoA12Repository certificadoA12Repository;

    //


}
