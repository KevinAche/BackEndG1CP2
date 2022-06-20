package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "evaluacion_TE")
public class Evaluacion_TE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvaluacionTE;

    private int puntajeTotal;

    @ManyToOne
    @JoinColumn(name = "idTutorEmpresarial",referencedColumnName = "idTutorEmpresarial")
    private TutorEmpresarial tutorEmpresarial;

    //Anexo12
    @Column(columnDefinition="text", length=10485760)
    private String docEvaluacionTE;

    public Evaluacion_TE() {
    }

    public Long getIdEvaluacionTE() {
        return idEvaluacionTE;
    }

    public void setIdEvaluacionTE(Long idEvaluacionTE) {
        this.idEvaluacionTE = idEvaluacionTE;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public TutorEmpresarial getTutorEmpresarial() {
        return tutorEmpresarial;
    }

    public void setTutorEmpresarial(TutorEmpresarial tutorEmpresarial) {
        this.tutorEmpresarial = tutorEmpresarial;
    }

    public String getDocEvaluacionTE() {
        return docEvaluacionTE;
    }

    public void setDocEvaluacionTE(String docEvaluacionTE) {
        this.docEvaluacionTE = docEvaluacionTE;
    }
}
