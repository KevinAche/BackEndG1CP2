package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCronograma;


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

    public TutorAcademico getTutorAcademico() {
        return tutorAcademico;
    }

    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.tutorAcademico = tutorAcademico;
    }

    public String getDocCronograma() {
        return docCronograma;
    }

    public void setDocCronograma(String docCronograma) {
        this.docCronograma = docCronograma;
    }
}
