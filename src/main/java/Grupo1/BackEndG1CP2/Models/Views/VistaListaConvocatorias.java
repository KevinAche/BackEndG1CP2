package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "lista_convocatorias_lanzadas")
@Immutable
public class VistaListaConvocatorias {

    @Id
    private Long id_convocatoria;

    private String doc_convocatoria;

    private String estado;

    private Date fecha_emision;

    private Date fecha_maxima;

    private String nombre_convocatoria;

    private Long id_solicitud_empresa;

    private String nombre_empresa;

    public VistaListaConvocatorias() {
    }

    public Long getId_convocatoria() {
        return id_convocatoria;
    }

    public void setId_convocatoria(Long id_convocatoria) {
        this.id_convocatoria = id_convocatoria;
    }

    public String getDoc_convocatoria() {
        return doc_convocatoria;
    }

    public void setDoc_convocatoria(String doc_convocatoria) {
        this.doc_convocatoria = doc_convocatoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getFecha_maxima() {
        return fecha_maxima;
    }

    public void setFecha_maxima(Date fecha_maxima) {
        this.fecha_maxima = fecha_maxima;
    }

    public String getNombre_convocatoria() {
        return nombre_convocatoria;
    }

    public void setNombre_convocatoria(String nombre_convocatoria) {
        this.nombre_convocatoria = nombre_convocatoria;
    }

    public Long getId_solicitud_empresa() {
        return id_solicitud_empresa;
    }

    public void setId_solicitud_empresa(Long id_solicitud_empresa) {
        this.id_solicitud_empresa = id_solicitud_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    @Override
    public String toString() {
        return "VistaListaConvocatorias{" +
                "id_convocatoria=" + id_convocatoria +
                ", doc_convocatoria='" + doc_convocatoria + '\'' +
                ", estado='" + estado + '\'' +
                ", fecha_emision=" + fecha_emision +
                ", fecha_maxima=" + fecha_maxima +
                ", nombre_convocatoria='" + nombre_convocatoria + '\'' +
                ", id_solicitud_empresa=" + id_solicitud_empresa +
                ", nombre_empresa='" + nombre_empresa + '\'' +
                '}';
    }
}
