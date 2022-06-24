package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos_solicitudes_aceptadas")
public class VistaAlumSolicitudes {

    @Id
    private Long id_alumno;

    private String cedula;

    private String a_nombres;

    private String a_apellidos;

    private String estado_solicitud;

    private Long id_empresa;

    private String nombre_empresa;

    public VistaAlumSolicitudes() {
    }

    public Long getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Long id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getA_nombres() {
        return a_nombres;
    }

    public void setA_nombres(String a_nombres) {
        this.a_nombres = a_nombres;
    }

    public String getA_apellidos() {
        return a_apellidos;
    }

    public void setA_apellidos(String a_apellidos) {
        this.a_apellidos = a_apellidos;
    }

    public String getEstado_solicitud() {
        return estado_solicitud;
    }

    public void setEstado_solicitud(String estado_solicitud) {
        this.estado_solicitud = estado_solicitud;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    @Override
    public String toString() {
        return "VistaAlumSolicitudes{" +
                "id_alumno=" + id_alumno +
                ", cedula='" + cedula + '\'' +
                ", a_nombres='" + a_nombres + '\'' +
                ", a_apellidos='" + a_apellidos + '\'' +
                ", estado_solicitud='" + estado_solicitud + '\'' +
                ", id_empresa=" + id_empresa +
                ", nombre_empresa='" + nombre_empresa + '\'' +
                '}';
    }
}
