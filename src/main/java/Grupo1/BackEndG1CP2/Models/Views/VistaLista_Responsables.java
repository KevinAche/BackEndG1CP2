package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lista_reponsables_ppp")
@Immutable
public class VistaLista_Responsables {

    @Id
    private Long id_responsableppp;

    private Long id_docente;
    
    private String titulo;

    private String abrev_titulo;

    private Long id_carrera;

    private String nombre_carrera;

    private Long id_persona;

    private String cedula;

    private String nombres_r;

    private String apellidos_r;


    public VistaLista_Responsables() {
    }

    public Long getId_responsableppp() {
        return id_responsableppp;
    }

    public void setId_responsableppp(Long id_responsableppp) {
        this.id_responsableppp = id_responsableppp;
    }

    public Long getId_docente() {
        return id_docente;
    }

    public void setId_docente(Long id_docente) {
        this.id_docente = id_docente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAbrev_titulo() {
        return abrev_titulo;
    }

    public void setAbrev_titulo(String abrev_titulo) {
        this.abrev_titulo = abrev_titulo;
    }

    public Long getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(Long id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres_r() {
        return nombres_r;
    }

    public void setNombres_r(String nombres_r) {
        this.nombres_r = nombres_r;
    }

    public String getApellidos_r() {
        return apellidos_r;
    }

    public void setApellidos_r(String apellidos_r) {
        this.apellidos_r = apellidos_r;
    }


    @Override
    public String toString() {
        return "VistaLista_Responsables{" +
                "id_responsableppp=" + id_responsableppp +
                ", id_docente=" + id_docente +
                ", titulo='" + titulo + '\'' +
                ", abrev_titulo='" + abrev_titulo + '\'' +
                ", id_carrera=" + id_carrera +
                ", nombre_carrera='" + nombre_carrera + '\'' +
                ", id_persona=" + id_persona +
                ", cedula='" + cedula + '\'' +
                ", nombres_r='" + nombres_r + '\'' +
                ", apellidos_r='" + apellidos_r + '\'' +
                '}';
    }
}
