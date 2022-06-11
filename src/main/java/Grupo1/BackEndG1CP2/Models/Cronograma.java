package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCronograma;

    @OneToMany(mappedBy = "cronograma")
    private List<Actividades_Cronograma> actividadesCronograma;

    @ManyToOne
    @JoinColumn(name = "idTutorAcademico",referencedColumnName = "idTutorAcademico")
    private TutorAcademico tutorAcademico;

    //ANEXO 10
    private String docCronograma;

    public Cronograma() {
    }

    public Long getIdCronograma() {
        return idCronograma;
    }

    public void setIdCronograma(Long idCronograma) {
        this.idCronograma = idCronograma;
    }

    public List<Actividades_Cronograma> getActividadesCronograma() {
        return actividadesCronograma;
    }

    public void setActividadesCronograma(List<Actividades_Cronograma> actividadesCronograma) {
        this.actividadesCronograma = actividadesCronograma;
    }
}
