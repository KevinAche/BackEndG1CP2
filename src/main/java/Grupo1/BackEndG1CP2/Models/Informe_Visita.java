package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informe_culminacion")
public class Informe_Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInformeVisita;

    private Date fecha;

    private String asunto;

    private String observaciones;

    private String horaInicio;

    private String horaFin;

    @ManyToOne
    @JoinColumn(name = "idRegistroVisitaE",referencedColumnName = "idRegistroVisitaE")
    private Registro_VisitaEmpresa registroVisitaEmpresa;

    public Informe_Visita() {
    }

    public Long getIdInformeVisita() {
        return idInformeVisita;
    }

    public void setIdInformeVisita(Long idInformeVisita) {
        this.idInformeVisita = idInformeVisita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Registro_VisitaEmpresa getRegistroVisitaEmpresa() {
        return registroVisitaEmpresa;
    }

    public void setRegistroVisitaEmpresa(Registro_VisitaEmpresa registroVisitaEmpresa) {
        this.registroVisitaEmpresa = registroVisitaEmpresa;
    }
}
