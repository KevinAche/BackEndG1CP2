package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "listar_tutores_emp")
@Immutable
public class VistaListarTutoresEmp {

    @Id
    private Long id_persona;

    private String cedula;

    private String nombres_t;

    private String apellidos_t;

    private String control;

    private String doc_asignacion;

    private String nombres_e;

    private String apellidos_e;

    public VistaListarTutoresEmp() {
    }

    public VistaListarTutoresEmp(Long id_persona, String cedula, String nombres_t, String apellidos_t, String control, String doc_asignacion, String nombres_e, String apellidos_e) {
        this.id_persona = id_persona;
        this.cedula = cedula;
        this.nombres_t = nombres_t;
        this.apellidos_t = apellidos_t;
        this.control = control;
        this.doc_asignacion = doc_asignacion;
        this.nombres_e = nombres_e;
        this.apellidos_e = apellidos_e;
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

    public String getNombres_t() {
        return nombres_t;
    }

    public void setNombres_t(String nombres_t) {
        this.nombres_t = nombres_t;
    }

    public String getApellidos_t() {
        return apellidos_t;
    }

    public void setApellidos_t(String apellidos_t) {
        this.apellidos_t = apellidos_t;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getDoc_asignacion() {
        return doc_asignacion;
    }

    public void setDoc_asignacion(String doc_asignacion) {
        this.doc_asignacion = doc_asignacion;
    }

    public String getNombres_e() {
        return nombres_e;
    }

    public void setNombres_e(String nombres_e) {
        this.nombres_e = nombres_e;
    }

    public String getApellidos_e() {
        return apellidos_e;
    }

    public void setApellidos_e(String apellidos_e) {
        this.apellidos_e = apellidos_e;
    }

    @Override
    public String toString() {
        return "VistaListarTutoresEmp{" +
                "id_persona=" + id_persona +
                ", cedula='" + cedula + '\'' +
                ", nombres_t='" + nombres_t + '\'' +
                ", apellidos_t='" + apellidos_t + '\'' +
                ", control='" + control + '\'' +
                ", doc_asignacion='" + doc_asignacion + '\'' +
                ", nombres_e='" + nombres_e + '\'' +
                ", apellidos_e='" + apellidos_e + '\'' +
                '}';
    }
}
