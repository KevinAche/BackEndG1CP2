package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.*;
import Grupo1.BackEndG1CP2.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionInformeCulminacion")
public class InformeCulminacionController {

    @Autowired
    private InformCulminaRepository informCulminaRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private SolicitudAlumnoRepository solicitudAlumnoRepository;

    @Autowired
    private ConvocatoriaRepository convocatoriaRepository;

    @Autowired
    private SolicitudEmpRepository solicitudEmpRepository;

    @Autowired
    private PersonalEmpresaRepository personalEmpresaRepository;

    @Autowired
    private TutorAcademicoRepository tutorAcademicoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private TutorEmpresarialRepository tutorEmpresarialRepository;

    @Autowired
    private RegistroAsistenciaRepository registroAsistenciaRepository;

    @GetMapping("/ListarInformesCulminacion")
    public ResponseEntity<RespuestaGenerica> ListarInformesCulminacion(){
        List<Informe_Culminacion> data = new ArrayList<>();
        RespuestaGenerica<Informe_Culminacion> respuesta = new RespuestaGenerica<>();
        try {
            data=informCulminaRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO INFORMES DE CULMINACION");
            respuesta.setData(data);
            respuesta.setEstado(0);
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al generar LISTADO DE INFORMES DE CULMINACION, causa ->"+e.getCause()+" || messagge->"+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearInformeCulminacion/{cedulaA}")
    public ResponseEntity<RespuestaGenerica> CrearInformeCulminacion(@RequestBody Informe_Culminacion informeEnviado ,@PathVariable String cedula){
        List<Informe_Culminacion> data = new ArrayList<>();
        RespuestaGenerica<Informe_Culminacion> respuesta = new RespuestaGenerica<>();
        HttpStatus estado  = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            Alumno alumno = alumnoRepository.findByPersona(persona);
            SolicitudAlumno solicitudAlumno = solicitudAlumnoRepository.findByAlumno(alumno);
            Empresa empresa=solicitudAlumno.getConvocatoria().getSolicitudEmpresa().getEmpleado().getEmpresa();
            TutorAcademico tutorAcademico = tutorAcademicoRepository.findByAlumno(alumno);
            TutorEmpresarial tutorEmpresarial = tutorEmpresarialRepository.findByAlumno(alumno);
            Registro_Asistencias registro_asistencias = registroAsistenciaRepository.findByAlumno(alumno);
            informeEnviado.setEmpresa(empresa);
            informeEnviado.setTutorEmpresarial(tutorEmpresarial);
            informeEnviado.setAlumno(alumno);
            informeEnviado.setTutorAcademico(tutorAcademico);
            data.add(informeEnviado);
            if(alumno !=null){
                respuesta.setMensaje("SE REGISTRO INFORME DE CULMINACION CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            }else{
                respuesta.setMensaje("NO SE REGISTRO INFORME DE CULMINACION CORRECTAMENTE DEBIDO A QUE LA CEDULA INGRESA -> "+cedula+" NO FUE ENCONTRADA");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado= HttpStatus.BAD_REQUEST;
            }
        }catch (Exception e){
            respuesta.setMensaje("Hubo un problema al insertar SolicitudEmpresa, causa ->"+e.getCause()+ " || message -> "+e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado= HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }





}
