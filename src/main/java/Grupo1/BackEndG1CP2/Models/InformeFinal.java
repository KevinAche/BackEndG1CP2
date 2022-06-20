package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informe_final")
public class InformeFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInformeFinal;

    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno alumno;

    //Anexo15
    @Column(columnDefinition="text", length=10485760)
    private String docInformeFinal;

    @PrePersist
    public void fecha(){
        this.fechaEmision=new Date();
    }

    public InformeFinal() {
    }

    public Long getIdInformeFinal() {
        return idInformeFinal;
    }

    public void setIdInformeFinal(Long idInformeFinal) {
        this.idInformeFinal = idInformeFinal;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getDocInformeFinal() {
        return docInformeFinal;
    }

    public void setDocInformeFinal(String docInformeFinal) {
        this.docInformeFinal = docInformeFinal;
    }
}
