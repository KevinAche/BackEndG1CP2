package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "cronograma")
public class Cronograma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estudiante;
    private String tutor_empre;
    private String empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getTutor_empre() {
        return tutor_empre;
    }

    public void setTutor_empre(String tutor_empre) {
        this.tutor_empre = tutor_empre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Cronograma{" +
                "id=" + id +
                ", estudiante='" + estudiante + '\'' +
                ", tutor_empre='" + tutor_empre + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }
}
