package Grupo1.BackEndG1CP2.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Grupo1.BackEndG1CP2.Models.Actividades_Cronograma;
import Grupo1.BackEndG1CP2.Models.RespuestaGenerica;
import Grupo1.BackEndG1CP2.Models.Views.ListaAnexo9;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarAnexo9Repository;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8100" })
@RequestMapping("/GestionAnexo9")
public class Anexo9Controller {
	
	@Autowired
	private ListarAnexo9Repository anexo9Repository;
	
	 @GetMapping("/ListaAnexo9")
	    public ResponseEntity<RespuestaGenerica> ListarDatosAnexo9(){
	        List<ListaAnexo9> data = new ArrayList<>();
	        RespuestaGenerica<ListaAnexo9> respuesta = new RespuestaGenerica<>();
	        try {
	            data=anexo9Repository.findAll();
	            respuesta.setMensaje("Se genero LISTADO DE DATOS ANEXO 9");
	            respuesta.setData(data);
	            respuesta.setEstado(0);
	        }catch (Exception e){
	            respuesta.setMensaje("Hubo un problema al generar LISTADO LISTADO DE DATOS ANEXO 9, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
	            respuesta.setData(data);
	            respuesta.setEstado(1);
	        }
	        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
	    }

}
