package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "acta_reunion")
public class ActaDeReunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActaReunion;

    private Date fechaEmision;

    private Date fechaInicioPPP;

    private Date fechaFinPPP;

    private String horario;

    //Anexo 7
    @Column(columnDefinition="text", length=10485760)
    private String docActaReunion;

    //Anexo 8
    @Column(columnDefinition="text", length=10485760)
    private String respuestaEstudiante;

    //Anexo 8.1
    @Column(columnDefinition="text", length=10485760)
    private String notificacionTA;

    @ManyToOne
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno alumno;


    public ActaDeReunion() {
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Long getIdActaReunion() {
        return idActaReunion;
    }

    public void setIdActaReunion(Long idActaReunion) {
        this.idActaReunion = idActaReunion;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaInicioPPP() {
        return fechaInicioPPP;
    }

    public void setFechaInicioPPP(Date fechaInicioPPP) {
        this.fechaInicioPPP = fechaInicioPPP;
    }

    public Date getFechaFinPPP() {
        return fechaFinPPP;
    }

    public void setFechaFinPPP(Date fechaFinPPP) {
        this.fechaFinPPP = fechaFinPPP;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDocActaReunion() {
        return docActaReunion;
    }

    public void setDocActaReunion(String docActaReunion) {
        this.docActaReunion = docActaReunion;
    }

    public String getRespuestaEstudiante() {
        return respuestaEstudiante;
    }

    public void setRespuestaEstudiante(String respuestaEstudiante) {
        this.respuestaEstudiante = respuestaEstudiante;
    }

    public String getNotificacionTA() {
        return notificacionTA;
    }

    public void setNotificacionTA(String notificacionTA) {
        this.notificacionTA = notificacionTA;
    }

}
