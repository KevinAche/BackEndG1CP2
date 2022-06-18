package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "lista_solicitudes_empresa")
@Immutable
public class VistaListarSolicitudesEmpresa {

    @Id
    Long id_solicitud_empresa;

    Date fecha_emision;

    Date fecha_inicio;

    Long numero_alumnos;

    String pdf_solicitud;

    String respuesta;

    String empleado;

    String responsable;

    public VistaListarSolicitudesEmpresa() {
    }

    public Long getId_solicitud_empresa() {
        return id_solicitud_empresa;
    }

    public void setId_solicitud_empresa(Long id_solicitud_empresa) {
        this.id_solicitud_empresa = id_solicitud_empresa;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Long getNumero_alumnos() {
        return numero_alumnos;
    }

    public void setNumero_alumnos(Long numero_alumnos) {
        this.numero_alumnos = numero_alumnos;
    }

    public String getPdf_solicitud() {
        return pdf_solicitud;
    }

    public void setPdf_solicitud(String pdf_solicitud) {
        this.pdf_solicitud = pdf_solicitud;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
