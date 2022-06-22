package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "lista_sol_generar")
@Immutable
public class VistaSolicitudesGenerar {

    @Id
    private Long id_solicitud_empresa;

    private Long id_empresa;

    private String nombre_empresa;

    private Date fecha_emision;

    private Date fecha_inicio;

    private String pdf_solicitud;

    private Long id_personal;
    
    private String nombre_emp;

    private String apellido_emp;

    private String carrera;

    private String abreviatura;

    public VistaSolicitudesGenerar() {
    }

    public Long getId_solicitud_empresa() {
        return id_solicitud_empresa;
    }

    public void setId_solicitud_empresa(Long id_solicitud_empresa) {
        this.id_solicitud_empresa = id_solicitud_empresa;
    }

    public Long getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(Long id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getPdf_solicitud() {
        return pdf_solicitud;
    }

    public void setPdf_solicitud(String pdf_solicitud) {
        this.pdf_solicitud = pdf_solicitud;
    }

    public Long getId_personal() {
        return id_personal;
    }

    public void setId_personal(Long id_personal) {
        this.id_personal = id_personal;
    }

    public String getNombre_emp() {
        return nombre_emp;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }

    public String getApellido_emp() {
        return apellido_emp;
    }

    public void setApellido_emp(String apellido_emp) {
        this.apellido_emp = apellido_emp;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
