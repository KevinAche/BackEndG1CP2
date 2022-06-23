package Grupo1.BackEndG1CP2.Controllers;

import Grupo1.BackEndG1CP2.Models.*;
import Grupo1.BackEndG1CP2.Models.Views.VistaListarSolicitudesEmpresa;
import Grupo1.BackEndG1CP2.Models.Views.VistaSolicitudesGenerar;
import Grupo1.BackEndG1CP2.Repositories.*;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarSolicitudesEmpresasRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.SolicitudesGenerarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/GestionSolicitudEmpresa")
public class SolicitudEmpresaController {

    @Autowired
    private SolicitudEmpRepository solicitudEmpRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonalEmpresaRepository personalEmpresaRepository;

    @Autowired
    private ListarSolicitudesEmpresasRepository listarSolicitudesEmpresasRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ResponsablePPPRepository responsablePPPRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private SolicitudesGenerarRepository solicitudesGenerarRepository;

    @GetMapping("/ListaSolicitudEmpresa")
    public ResponseEntity<RespuestaGenerica> ListarSolicitudEmpresa() {
        List<SolicitudEmpresa> data = new ArrayList<>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        try {
            data = solicitudEmpRepository.findAll();
            respuesta.setMensaje("Se genero LISTADO SOLICITUDES EMPRESA");
            respuesta.setData(data);
            respuesta.setEstado(0);
        } catch (Exception e) {
            respuesta.setMensaje("Hubo un problema al generar LISTADO SOLICITUDES EMPRESA, causa ->" + e.getCause() + " || messagge->" + e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, HttpStatus.OK);
    }


    @PostMapping("/CrearSolicitudEmpresa/{cedula_empleado}/{cedula_responsable}")
    public ResponseEntity<RespuestaGenerica> CrearSolicitudEmpresa(@RequestBody SolicitudEmpresa solicitudEmpresaEnviada,
                                                                   @PathVariable String cedula_empleado,
                                                                   @PathVariable String cedula_responsable) {
        List<SolicitudEmpresa> data = new ArrayList<>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado = HttpStatus.CREATED;
        try {
            Persona persona = personaRepository.findByCedula(cedula_empleado);
            PersonalEmpresa personalEmpresa = personalEmpresaRepository.findByPersona(persona);
            Persona responsable = personaRepository.findByCedula(cedula_responsable);
            Docente docente = docenteRepository.findByPersona(responsable);
            ResponsablePPP responsablePPP = responsablePPPRepository.findByDocente(docente);
            Carrera carrera = carreraRepository.findById(docente.getCarrera().getIdCarrera()).get();
            if (persona != null && responsable != null && carrera != null) {
                solicitudEmpresaEnviada.setResponsablePPP(responsablePPP);
                solicitudEmpresaEnviada.setEmpleado(personalEmpresa);
                solicitudEmpresaEnviada.setCarrera(carrera);
                SolicitudEmpresa solicitudEmpresa = solicitudEmpRepository.save(solicitudEmpresaEnviada);
                data.add(solicitudEmpresa);
                if (solicitudEmpresa != null) {
                    respuesta.setMensaje("SE REGISTRO SOLICITUD EMPRESA CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(0);
                } else {
                    respuesta.setMensaje("NO SE REGISTRO SOLICITUD EMPRESA CORRECTAMENTE");
                    respuesta.setData(data);
                    respuesta.setEstado(1);
                    estado = HttpStatus.BAD_REQUEST;
                }
            } else {
                respuesta.setMensaje("NO SE REGISTRO SOLICITUD EMPRESA DEBIDOA LOS CREDENCUALES ENVIADOS");
                respuesta.setData(data);
                respuesta.setEstado(1);
                estado = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            respuesta.setMensaje("Hubo un problema al insertar SOLICITUD EMPRESA, causa ->" + e.getCause() + " || message -> " + e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


    @PutMapping("/EditarSolicitudEmpresa/{id}")
    public ResponseEntity<RespuestaGenerica> EditarSolicitudEmpresa(@RequestBody SolicitudEmpresa solicitudEmpresaEnviada, @PathVariable Long id) {
        List<SolicitudEmpresa> data = new ArrayList<>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        AtomicReference<HttpStatus> estado = new AtomicReference<>(HttpStatus.OK);
        try {
            SolicitudEmpresa soli = solicitudEmpRepository.findById(id)
                    .map(res -> {
                        res.setFechaInicio(solicitudEmpresaEnviada.getFechaInicio());
                        res.setNumeroAlumnos(solicitudEmpresaEnviada.getNumeroAlumnos());
                        res.setEstado(solicitudEmpresaEnviada.isEstado());
                        res.setPdfSolicitud(solicitudEmpresaEnviada.getPdfSolicitud());
                        res.setRespuesta(solicitudEmpresaEnviada.getRespuesta());

                        //EN CASO DE ENCONTRAR SE ANADE DATA A RESPUESTA
                        data.add(res);
                        respuesta.setMensaje("SE MODIFICO SolicitudEmpresa CORRECTAMENTE");
                        respuesta.setData(data);
                        respuesta.setEstado(0);
                        //SE RETORNA PERSONA MODIFICADA
                        return solicitudEmpRepository.save(res);
                    })
                    .orElseGet(() -> {
                        respuesta.setMensaje("NO SE ENCONTRO SolicitudEmpresa CON EL ID INGRESADO: " + id);
                        respuesta.setData(data);
                        respuesta.setEstado(1);
                        estado.set(HttpStatus.BAD_REQUEST);
                        return new SolicitudEmpresa();
                    });
        } catch (Exception e) {
            respuesta.setMensaje("Hubo un problema al MODIFICAR SolicitudEmpresa, causa ->" + e.getCause() + " || message -> " + e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
            estado.set(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<RespuestaGenerica>(respuesta, estado.get());
    }


    @DeleteMapping("/EliminarSolicitud/{id}")
    public ResponseEntity EliminarSolicitudEmpresa(@PathVariable Long id) {
        List<SolicitudEmpresa> data = new ArrayList<SolicitudEmpresa>();
        RespuestaGenerica<SolicitudEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado = HttpStatus.OK;

        try {

            solicitudEmpRepository.deleteById(id);
            if (solicitudEmpRepository != null) {
                data.add(new SolicitudEmpresa());
                respuesta.setMensaje("SE ELIMINO SolicitudEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            } else {
                estado = HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE ELIMINO SolicitudEmpresa CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado = HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR SolicitudEmpresa, causa->" + e.getCause() + " ||  message -> " + e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


    @GetMapping("/ListarSolicitudesEmpresa/{cedula}")
    public ResponseEntity GenerarListarSolicitudes(@PathVariable String cedula) {
        List<VistaListarSolicitudesEmpresa> data = new ArrayList<VistaListarSolicitudesEmpresa>();
        RespuestaGenerica<VistaListarSolicitudesEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado = HttpStatus.OK;
        try {
            //SE VE LA EL EMPLEADO QUE SE LOGUE
            Persona persona = personaRepository.findByCedula(cedula);
            PersonalEmpresa personalEmpresa = personalEmpresaRepository.findByPersona(persona);
            List<SolicitudEmpresa> solicitudEmpresasGeneral = solicitudEmpRepository.findAll();

            List<VistaListarSolicitudesEmpresa> vistaGeneral = listarSolicitudesEmpresasRepository.findAll();

            for (SolicitudEmpresa sol : solicitudEmpresasGeneral) {
                if (sol.getEmpleado().getEmpresa() == personalEmpresa.getEmpresa()) {
                    for (VistaListarSolicitudesEmpresa vista : vistaGeneral) {
                        if (vista.getId_solicitud_empresa() == sol.getIdSolicitudEmpresa()) {
                            data.add(vista);
                        }
                    }
                }
            }

            if (data.size() >= 0) {
                respuesta.setMensaje("SE LISTO SOLICITUDES DE ESTA EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            } else {
                estado = HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE LISTO SOLICITUD DE EMPRESAS CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado = HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR SolicitudEmpresa, causa->" + e.getCause() + " ||  message -> " + e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


    @GetMapping("/ListarSolicitudesEmpresaVista")
    public ResponseEntity GenerarListarSolicitudes() {
        List<VistaListarSolicitudesEmpresa> data = new ArrayList<VistaListarSolicitudesEmpresa>();
        RespuestaGenerica<VistaListarSolicitudesEmpresa> respuesta = new RespuestaGenerica<>();
        HttpStatus estado = HttpStatus.OK;
        try {
            List<VistaListarSolicitudesEmpresa> lISTA = listarSolicitudesEmpresasRepository.findAll();
            if (lISTA.size() >= 0) {
                data.addAll(lISTA);
                respuesta.setMensaje("SE LISTO SOLICITUDES DE ESTA EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            } else {
                estado = HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE LISTO SOLICITUD DE EMPRESAS CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado = HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al ELIMINAR SolicitudEmpresa, causa->" + e.getCause() + " ||  message -> " + e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


    @GetMapping("/ListarSolicitudesGenerarVista")
    public ResponseEntity GenerarListarAsignar() {
        List<VistaSolicitudesGenerar> data = new ArrayList<VistaSolicitudesGenerar>();
        RespuestaGenerica<VistaSolicitudesGenerar> respuesta = new RespuestaGenerica<>();
        HttpStatus estado = HttpStatus.OK;
        try {
            List<VistaSolicitudesGenerar> lISTA = solicitudesGenerarRepository.findAll();
            if (lISTA.size() >= 0) {
                data.addAll(lISTA);
                respuesta.setMensaje("SE LISTO SOLICITUDES DE ESTA EMPRESA CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(0);
            } else {
                estado = HttpStatus.BAD_REQUEST;
                data.add(null);
                respuesta.setMensaje("NO SE LISTO SOLICITUD DE EMPRESAS CORRECTAMENTE");
                respuesta.setData(data);
                respuesta.setEstado(1);
            }
        } catch (Exception e) {
            estado = HttpStatus.BAD_REQUEST;
            respuesta.setMensaje("Hubo un problema al LISTAR SOLICITUDES, causa->" + e.getCause() + " ||  message -> " + e.getMessage());
            respuesta.setData(data);
            respuesta.setEstado(1);
        }

        return new ResponseEntity<RespuestaGenerica>(respuesta, estado);
    }


}
