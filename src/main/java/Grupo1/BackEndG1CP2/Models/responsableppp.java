package Grupo1.BackEndG1CP2.Models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "responsablepp")
public class responsableppp {
    private String empresa;

    public responsableppp(String empresa) {
        this.empresa = empresa;
    }

    public responsableppp() {
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
