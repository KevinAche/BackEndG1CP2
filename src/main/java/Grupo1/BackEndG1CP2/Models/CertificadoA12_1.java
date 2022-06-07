package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "certificadoA12_1")
public class CertificadoA12_1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private String carrera;

    private Date fecha_inicio;

    private Date fecha_fin;

    private String actividades;

    private String horas;

    private float calificacion;

    private String nombre;

    private String ced_tutor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCed_tutor() {
        return ced_tutor;
    }

    public void setCed_tutor(String ced_tutor) {
        this.ced_tutor = ced_tutor;
    }

    @Override
    public String toString() {
        return "CertificadoA12_1{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", carrera='" + carrera + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", actividades='" + actividades + '\'' +
                ", horas='" + horas + '\'' +
                ", calificacion=" + calificacion +
                ", nombre='" + nombre + '\'' +
                ", ced_tutor='" + ced_tutor + '\'' +
                '}';
    }
}
