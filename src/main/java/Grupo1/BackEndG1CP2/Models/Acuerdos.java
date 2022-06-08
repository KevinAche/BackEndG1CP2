package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "acuerdos")
public class Acuerdos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcuerdo;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idActaReunion", referencedColumnName = "idActaReunion")
    private ActaDeReunion actaDeReunion;

    public Acuerdos() {
    }

    public Long getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Long idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ActaDeReunion getActaDeReunion() {
        return actaDeReunion;
    }

    public void setActaDeReunion(ActaDeReunion actaDeReunion) {
        this.actaDeReunion = actaDeReunion;
    }
}
