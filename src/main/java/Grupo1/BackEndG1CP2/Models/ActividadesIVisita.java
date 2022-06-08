package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "actividades_ivisita")
public class ActividadesIVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividadesVisita;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idRegistroVisitaE",referencedColumnName = "idRegistroVisitaE")
    private Registro_VisitaEmpresa registroVisitaEmpresa;

    public ActividadesIVisita() {
    }

    public Long getIdActividadesVisita() {
        return idActividadesVisita;
    }

    public void setIdActividadesVisita(Long idActividadesVisita) {
        this.idActividadesVisita = idActividadesVisita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Registro_VisitaEmpresa getRegistroVisitaEmpresa() {
        return registroVisitaEmpresa;
    }

    public void setRegistroVisitaEmpresa(Registro_VisitaEmpresa registroVisitaEmpresa) {
        this.registroVisitaEmpresa = registroVisitaEmpresa;
    }
}
