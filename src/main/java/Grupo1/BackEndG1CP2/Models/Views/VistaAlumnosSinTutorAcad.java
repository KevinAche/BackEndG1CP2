package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "obtener_empresa_desig_tutor_a")
@Immutable
public class VistaAlumnosSinTutorAcad {

    @Id
    Long id_alumno;

    String cedula;

    String nombres;

    String apellidos;

    String ciclo;

    String paralelo;

    double promedio;

    String carrera;

    Long id_solicitud_alumno;

    Long id_convocatoria;

    Long id_solicitud_empresa;

    String nombre_empresa;

    public VistaAlumnosSinTutorAcad() {
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Long getId_solicitud_alumno() {
        return id_solicitud_alumno;
    }

    public void setId_solicitud_alumno(Long id_solicitud_alumno) {
        this.id_solicitud_alumno = id_solicitud_alumno;
    }

    public Long getId_convocatoria() {
        return id_convocatoria;
    }

    public void setId_convocatoria(Long id_convocatoria) {
        this.id_convocatoria = id_convocatoria;
    }

    public Long getId_solicitud_empresa() {
        return id_solicitud_empresa;
    }

    public void setId_solicitud_empresa(Long id_solicitud_empresa) {
        this.id_solicitud_empresa = id_solicitud_empresa;
    }

    @Override
    public String toString() {
        return "VistaAlumnosSinTutorAcad{" +
                "id_alumno=" + id_alumno +
                ", cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", ciclo='" + ciclo + '\'' +
                ", paralelo='" + paralelo + '\'' +
                ", promedio=" + promedio +
                ", carrera='" + carrera + '\'' +
                ", id_solicitud_alumno=" + id_solicitud_alumno +
                ", id_convocatoria=" + id_convocatoria +
                ", id_solicitud_empresa=" + id_solicitud_empresa +
                '}';
    }
}
