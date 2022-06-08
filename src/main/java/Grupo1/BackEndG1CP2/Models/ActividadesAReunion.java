package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "actividades_areunion")
public class ActividadesAReunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividadesAR;

    private int numSemanas;

    private double numHoras;

    @ManyToOne
    @JoinColumn(name = "idActividad", referencedColumnName = "idActividad")
    private Actividades actividades;

    @ManyToOne
    @JoinColumn(name = "idActaReunion",referencedColumnName = "idActaReunion")
    private ActaDeReunion actaDeReunion;

    public ActividadesAReunion() {
    }

    public Long getIdActividadesAR() {
        return idActividadesAR;
    }

    public void setIdActividadesAR(Long idActividadesAR) {
        this.idActividadesAR = idActividadesAR;
    }

    public int getNumSemanas() {
        return numSemanas;
    }

    public void setNumSemanas(int numSemanas) {
        this.numSemanas = numSemanas;
    }

    public double getNumHoras() {
        return numHoras;
    }

    public void setNumHoras(double numHoras) {
        this.numHoras = numHoras;
    }

    public Actividades getActividades() {
        return actividades;
    }

    public void setActividades(Actividades actividades) {
        this.actividades = actividades;
    }

    public ActaDeReunion getActaDeReunion() {
        return actaDeReunion;
    }

    public void setActaDeReunion(ActaDeReunion actaDeReunion) {
        this.actaDeReunion = actaDeReunion;
    }
}
