package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "evaluacionA12")
public class EvaluacionA12 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresa;

    private String carrera;

    private Date fecha_inicio;

    private Date fecha_fin;

    private String puntaje_total;

    private String horas_total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

    public String getPuntaje_total() {
        return puntaje_total;
    }

    public void setPuntaje_total(String puntaje_total) {
        this.puntaje_total = puntaje_total;
    }

    public String getHoras_total() {
        return horas_total;
    }

    public void setHoras_total(String horas_total) {
        this.horas_total = horas_total;
    }

    @Override
    public String toString() {
        return "EvaluacionA12{" +
                "id=" + id +
                ", empresa='" + empresa + '\'' +
                ", carrera='" + carrera + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", puntaje_total='" + puntaje_total + '\'' +
                ", horas_total='" + horas_total + '\'' +
                '}';
    }
}
