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

    private Long id_solicitud_empresa;

    private Long id_responsableppp;

    private Long cedula_rp;

    private String tit_rp;

    private String rp_nombres;

    private String rp_apellidos;

    private String carrera_rp;

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

    public Long getId_solicitud_empresa() {
        return id_solicitud_empresa;
    }

    public void setId_solicitud_empresa(Long id_solicitud_empresa) {
        this.id_solicitud_empresa = id_solicitud_empresa;
    }

    public String getTit_rp() {
        return tit_rp;
    }

    public void setTit_rp(String tit_rp) {
        this.tit_rp = tit_rp;
    }

    public Long getId_responsableppp() {
        return id_responsableppp;
    }

    public void setId_responsableppp(Long id_responsableppp) {
        this.id_responsableppp = id_responsableppp;
    }

    public Long getCedula_rp() {
        return cedula_rp;
    }

    public void setCedula_rp(Long cedula_rp) {
        this.cedula_rp = cedula_rp;
    }

    public String getRp_nombres() {
        return rp_nombres;
    }

    public void setRp_nombres(String rp_nombres) {
        this.rp_nombres = rp_nombres;
    }

    public String getRp_apellidos() {
        return rp_apellidos;
    }

    public void setRp_apellidos(String rp_apellidos) {
        this.rp_apellidos = rp_apellidos;
    }

    public String getCarrera_rp() {
        return carrera_rp;
    }

    public void setCarrera_rp(String carrera_rp) {
        this.carrera_rp = carrera_rp;
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
