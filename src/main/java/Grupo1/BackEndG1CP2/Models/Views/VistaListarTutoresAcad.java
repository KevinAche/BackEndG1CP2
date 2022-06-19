package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lista_tutores_acad")
@Immutable
public class VistaListarTutoresAcad {

    @Id
    private Long id_tutor_academico;

    private String a_nombres;

    private String a_apellidos;

    private String d_nombres;

    private String d_apellidos;

    @Column(length = 10485760)
    private String doc_asignacion;

    public VistaListarTutoresAcad() {
    }

    public Long getId_tutor_academico() {
        return id_tutor_academico;
    }

    public void setId_tutor_academico(Long id_tutor_academico) {
        this.id_tutor_academico = id_tutor_academico;
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

    public String getD_nombres() {
        return d_nombres;
    }

    public void setD_nombres(String d_nombres) {
        this.d_nombres = d_nombres;
    }

    public String getD_apellidos() {
        return d_apellidos;
    }

    public void setD_apellidos(String d_apellidos) {
        this.d_apellidos = d_apellidos;
    }

    public String getDoc_asignacion() {
        return doc_asignacion;
    }

    public void setDoc_asignacion(String doc_asignacion) {
        this.doc_asignacion = doc_asignacion;
    }
}
