package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "evaluacion_ta")
public class Evaluacion_TA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estudiante;

    private String empresa;

    private String carrera;

    private Date fecha_inicio;

    private Date fecha_fin;

    private Float porcentaje_total;

    private int numero_horas;

    private int tutor_empresarial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
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

    public Float getPorcentaje_total() {
        return porcentaje_total;
    }

    public void setPorcentaje_total(Float porcentaje_total) {
        this.porcentaje_total = porcentaje_total;
    }

    public int getNumero_horas() {
        return numero_horas;
    }

    public void setNumero_horas(int numero_horas) {
        this.numero_horas = numero_horas;
    }

    public int getTutor_empresarial() {
        return tutor_empresarial;
    }

    public void setTutor_empresarial(int tutor_empresarial) {
        this.tutor_empresarial = tutor_empresarial;
    }

    @Override
    public String toString() {
        return "Evaluacion_TA{" +
                "id=" + id +
                ", estudiante='" + estudiante + '\'' +
                ", empresa='" + empresa + '\'' +
                ", carrera='" + carrera + '\'' +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", porcentaje_total=" + porcentaje_total +
                ", numero_horas=" + numero_horas +
                ", tutor_empresarial=" + tutor_empresarial +
                '}';
    }
}
