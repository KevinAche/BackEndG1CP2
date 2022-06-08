package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "evaluacion_ta")
public class Evaluacion_TA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacionTA;

    private int puntajeTotal;

    @ManyToOne
    @JoinColumn(name = "idTutorAcademico",referencedColumnName = "idTutorAcademico")
    private TutorAcademico tutorAcademico;

    //Anexo14
    private String docEvaluacionTA;

    public Evaluacion_TA() {
    }

    public Long getIdEvaluacionTA() {
        return idEvaluacionTA;
    }

    public void setIdEvaluacionTA(Long idEvaluacionTA) {
        this.idEvaluacionTA = idEvaluacionTA;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public TutorAcademico getTutorAcademico() {
        return tutorAcademico;
    }

    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.tutorAcademico = tutorAcademico;
    }

    public String getDocEvaluacionTA() {
        return docEvaluacionTA;
    }

    public void setDocEvaluacionTA(String docEvaluacionTA) {
        this.docEvaluacionTA = docEvaluacionTA;
    }
}
