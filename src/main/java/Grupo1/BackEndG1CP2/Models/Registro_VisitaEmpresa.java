package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "reg_visEmpresa")
public class Registro_VisitaEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tutor_Academico;

    private String empresa;

    private String estudiante;

    private String preiodo_academico;

    private String ciclo;

    private String tutor_empresarial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTutor_Academico() {
        return tutor_Academico;
    }

    public void setTutor_Academico(String tutor_Academico) {
        this.tutor_Academico = tutor_Academico;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getPreiodo_academico() {
        return preiodo_academico;
    }

    public void setPreiodo_academico(String preiodo_academico) {
        this.preiodo_academico = preiodo_academico;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getTutor_empresarial() {
        return tutor_empresarial;
    }

    public void setTutor_empresarial(String tutor_empresarial) {
        this.tutor_empresarial = tutor_empresarial;
    }

    @Override
    public String toString() {
        return "Registro_VisitaEmpresa{" +
                "id=" + id +
                ", tutor_Academico='" + tutor_Academico + '\'' +
                ", empresa='" + empresa + '\'' +
                ", estudiante='" + estudiante + '\'' +
                ", preiodo_academico='" + preiodo_academico + '\'' +
                ", ciclo='" + ciclo + '\'' +
                ", tutor_empresarial='" + tutor_empresarial + '\'' +
                '}';
    }
}
