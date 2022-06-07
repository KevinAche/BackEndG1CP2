package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "informe_culminacion")
public class Informe_Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private String horaInicio;
    private String horaFin;

    private String Asunto;
    private String Actividades;
    private String Comentarios;
//    Esta bien este atributo ?
    private String Firma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String asunto) {
        Asunto = asunto;
    }

    public String getActividades() {
        return Actividades;
    }

    public void setActividades(String actividades) {
        Actividades = actividades;
    }

    public String getComentarios() {
        return Comentarios;
    }

    public void setComentarios(String comentarios) {
        Comentarios = comentarios;
    }

    public String getFirma() {
        return Firma;
    }

    public void setFirma(String firma) {
        Firma = firma;
    }

    @Override
    public String   toString() {
        return "Informe_Visita{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                ", Asunto='" + Asunto + '\'' +
                ", Actividades='" + Actividades + '\'' +
                ", Comentarios='" + Comentarios + '\'' +
                ", Firma='" + Firma + '\'' +
                '}';
    }
}
