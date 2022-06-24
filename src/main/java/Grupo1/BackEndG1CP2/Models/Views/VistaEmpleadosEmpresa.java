package Grupo1.BackEndG1CP2.Models.Views;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lista_empleado_mi_empresa")
@Immutable
public class VistaEmpleadosEmpresa {

    @Id
    private Long id_personal;

    private String cedula;

    private String p_nombres;

    private String p_apellidos;

    private String abrev_titulo;

    private String cargo;

    private Long id_empresa;

    private String nombre_empresa;


    public VistaEmpleadosEmpresa() {
    }

    public Long getId_personal() {
        return id_personal;
    }

    public void setId_personal(Long id_personal) {
        this.id_personal = id_personal;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getP_nombres() {
        return p_nombres;
    }

    public void setP_nombres(String p_nombres) {
        this.p_nombres = p_nombres;
    }

    public String getP_apellidos() {
        return p_apellidos;
    }

    public void setP_apellidos(String p_apellidos) {
        this.p_apellidos = p_apellidos;
    }

    public String getAbrev_titulo() {
        return abrev_titulo;
    }

    public void setAbrev_titulo(String abrev_titulo) {
        this.abrev_titulo = abrev_titulo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
                "id_personal=" + id_personal +
                ", cedula='" + cedula + '\'' +
                ", p_nombres='" + p_nombres + '\'' +
                ", p_apellidos='" + p_apellidos + '\'' +
                ", abrev_titulo='" + abrev_titulo + '\'' +
                ", cargo='" + cargo + '\'' +
                ", id_empresa=" + id_empresa +
                ", nombre_empresa='" + nombre_empresa + '\'' +
                '}';
    }
}
