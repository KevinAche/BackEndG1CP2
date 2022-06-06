package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

    @Entity
@Table(name = "t_empresarial")
public class Tutor_empresarial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "tutor_empresarial{" +
                "id=" + id +
                '}';
    }
}
