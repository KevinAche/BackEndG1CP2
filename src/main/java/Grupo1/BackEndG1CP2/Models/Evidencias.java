package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "evidencias")
public class Evidencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvidencias;

    private String descripcion;

    //se refiere a la imagen u otro tipo de archivo
    private String anexos;

    @ManyToOne
    @JoinColumn(name = "idInformeCulminacion", referencedColumnName = "idInformeCulminacion")
    private Informe_Culminacion informeCulminacion;

    public Evidencias() {
    }

    public Long getIdEvidencias() {
        return idEvidencias;
    }

    public void setIdEvidencias(Long idEvidencias) {
        this.idEvidencias = idEvidencias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnexos() {
        return anexos;
    }

    public void setAnexos(String anexos) {
        this.anexos = anexos;
    }

    public Informe_Culminacion getInformeCulminacion() {
        return informeCulminacion;
    }

    public void setInformeCulminacion(Informe_Culminacion informeCulminacion) {
        this.informeCulminacion = informeCulminacion;
    }
}
