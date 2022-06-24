package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tutores_empresa")
@Immutable
public class VistaTutoresEmpresa {

    @Id
    private Long id_tutor_empresarial;

    private String a_nombres;

    private String a_apellidos;

    private String e_nombres;

    private String e_apellidos;

    private String doc_asignacion;

    private Long id_empresa;

    private String nombre_empresa;

    public VistaTutoresEmpresa() {
    }

    public Long getId_tutor_empresarial() {
        return id_tutor_empresarial;
    }

    public void setId_tutor_empresarial(Long id_tutor_empresarial) {
        this.id_tutor_empresarial = id_tutor_empresarial;
    }

    public String getA_nombres() {
        return a_nombres;
    }

    public void setA_nombres(String a_nombres) {
        this.a_nombres = a_nombres;
    }

    public String getA_apellidos() {
        return a_apellidos;
    }

    public void setA_apellidos(String a_apellidos) {
        this.a_apellidos = a_apellidos;
    }

    public String getE_nombres() {
        return e_nombres;
    }

    public void setE_nombres(String e_nombres) {
        this.e_nombres = e_nombres;
    }

    public String getE_apellidos() {
        return e_apellidos;
    }

    public void setE_apellidos(String e_apellidos) {
        this.e_apellidos = e_apellidos;
    }

    public String getDoc_asignacion() {
        return doc_asignacion;
    }

    public void setDoc_asignacion(String doc_asignacion) {
        this.doc_asignacion = doc_asignacion;
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

    @Override
    public String toString() {
        return "VistaEmpleadosEmpresa{" +
                "id_tutor_empresarial=" + id_tutor_empresarial +
                ", a_nombres='" + a_nombres + '\'' +
                ", a_apellidos='" + a_apellidos + '\'' +
                ", e_nombres='" + e_nombres + '\'' +
                ", e_apellidos='" + e_apellidos + '\'' +
                ", doc_asignacion='" + doc_asignacion + '\'' +
                ", id_empresa=" + id_empresa +
                ", nombre_empresa='" + nombre_empresa + '\'' +
                '}';
    }
}
