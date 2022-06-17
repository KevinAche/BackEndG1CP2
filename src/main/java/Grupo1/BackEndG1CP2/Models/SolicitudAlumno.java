package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SolicitudAlumno")
public class SolicitudAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitudAlumno;

    private Date fechaEmision;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "idConvocatoria", referencedColumnName = "idConvocatoria")
    private Convocatoria convocatoria;

    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno")
    private Alumno alumno;

    private int horasPPP;

    public SolicitudAlumno() {
    }

    
    public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Long getIdSolicitudAlumno() {
        return idSolicitudAlumno;
    }

    public void setIdSolicitudAlumno(Long idSolicitudAlumno) {
        this.idSolicitudAlumno = idSolicitudAlumno;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Convocatoria getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatoria convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public int getHorasPPP() {
        return horasPPP;
    }

    public void setHorasPPP(int horasPPP) {
        this.horasPPP = horasPPP;
    }
}
