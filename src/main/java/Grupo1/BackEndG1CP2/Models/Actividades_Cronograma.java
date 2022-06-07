package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "act_cronograma")
public class Actividades_Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int num_actividad;

    private Date fecha_seguimiento;

    private Date fecha_finalizacion;

    private float porcentaje;

    private String detalle;

    private String actividades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNum_actividad() {
        return num_actividad;
    }

    public void setNum_actividad(int num_actividad) {
        this.num_actividad = num_actividad;
    }

    public Date getFecha_seguimiento() {
        return fecha_seguimiento;
    }

    public void setFecha_seguimiento(Date fecha_seguimiento) {
        this.fecha_seguimiento = fecha_seguimiento;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public float getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(float porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    @Override
    public String  toString() {
        return "Actividades_Cronograma{" +
                "id=" + id +
                ", num_actividad=" + num_actividad +
                ", fecha_seguimiento=" + fecha_seguimiento +
                ", fecha_finalizacion=" + fecha_finalizacion +
                ", porcentaje=" + porcentaje +
                ", detalle='" + detalle + '\'' +
                ", actividades='" + actividades + '\'' +
                '}';
    }
}
