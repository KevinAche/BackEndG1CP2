package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "convocatoria")
public class Convocatoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConvocatoria;

    private String nombreConvocatoria;

    private Date fechaEmision;

    private Date fechaMaxima;

    //Anexo 2
    @Column(columnDefinition="text", length=10485760)
    private String docConvocatoria;

    @OneToOne
    @JoinColumn(name = "idSolicitudEmpresa",referencedColumnName = "idSolicitudEmpresa")
    private SolicitudEmpresa solicitudEmpresa;

    public Convocatoria() {
    }


    public Long getIdConvocatoria() {
        return idConvocatoria;
    }

    public void setIdConvocatoria(Long idConvocatoria) {
        this.idConvocatoria = idConvocatoria;
    }

    public String getNombreConvocatoria() {
        return nombreConvocatoria;
    }

    public void setNombreConvocatoria(String nombreConvocatoria) {
        this.nombreConvocatoria = nombreConvocatoria;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getDocConvocatoria() {
        return docConvocatoria;
    }

    public void setDocConvocatoria(String docConvocatoria) {
        this.docConvocatoria = docConvocatoria;
    }

    public SolicitudEmpresa getSolicitudEmpresa() {
        return solicitudEmpresa;
    }

    public void setSolicitudEmpresa(SolicitudEmpresa solicitudEmpresa) {
        this.solicitudEmpresa = solicitudEmpresa;
    }
}
