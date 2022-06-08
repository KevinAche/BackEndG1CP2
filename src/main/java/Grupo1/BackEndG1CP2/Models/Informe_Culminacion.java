package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informe_culminacion")
public class Informe_Culminacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInformeCulminacion;

    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno alumno;

    public Informe_Culminacion() {
    }

    public Long getIdInformeCulminacion() {
        return idInformeCulminacion;
    }

    public void setIdInformeCulminacion(Long idInformeCulminacion) {
        this.idInformeCulminacion = idInformeCulminacion;
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
}
