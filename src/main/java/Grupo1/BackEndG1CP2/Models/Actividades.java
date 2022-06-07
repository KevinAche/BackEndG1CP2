package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "actividades")
public class Actividades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;
    
    private String descripcion;
    
    private String area;
    
    @ManyToOne
    @JoinColumn(name = "idAsignatura", referencedColumnName = "idAsignatura")
    private Asignaturas asignatura;
    
    @ManyToOne
    @JoinColumn(name = "idSolicitudEmpresa", referencedColumnName = "idSolicitudEmpresa")
    private SolicitudEmpresa solicitudEmpresa;

    public Actividades() {
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

    public SolicitudEmpresa getSolicitudEmpresa() {
        return solicitudEmpresa;
    }

    public void setSolicitudEmpresa(SolicitudEmpresa solicitudEmpresa) {
        this.solicitudEmpresa = solicitudEmpresa;
    }
}
