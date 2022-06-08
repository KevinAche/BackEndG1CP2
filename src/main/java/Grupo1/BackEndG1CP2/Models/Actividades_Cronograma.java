package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "act_cronograma")
public class Actividades_Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numActividad;

    private Date fechaSeguimiento;

    private Date fechaFinalizacion;

    private double porcentaje;

    private String observacion;

    @OneToOne
    @JoinColumn(name = "idActividadesD",referencedColumnName = "idActividadesD")
    private Actividades_Diarias actividadesDiarias;

    @ManyToOne
    @JoinColumn(name = "idCronograma",referencedColumnName = "idCronograma" )
    private Cronograma cronograma;

    public Actividades_Cronograma() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumActividad() {
        return numActividad;
    }

    public void setNumActividad(int numActividad) {
        this.numActividad = numActividad;
    }

    public Date getFechaSeguimiento() {
        return fechaSeguimiento;
    }

    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Actividades_Diarias getActividadesDiarias() {
        return actividadesDiarias;
    }

    public void setActividadesDiarias(Actividades_Diarias actividadesDiarias) {
        this.actividadesDiarias = actividadesDiarias;
    }

    public Cronograma getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronograma cronograma) {
        this.cronograma = cronograma;
    }
}
