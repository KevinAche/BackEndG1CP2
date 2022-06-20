package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "reg_visEmpresa")
public class Registro_VisitaEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroVisitaE;

    private String observaciones;

    //Anexo 11
    @Column(columnDefinition="text", length=10485760)
    private String docRegistroVisita;

    @OneToOne
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno alumno;

    public Registro_VisitaEmpresa() {
    }

    public Long getIdRegistroVisitaE() {
        return idRegistroVisitaE;
    }

    public void setIdRegistroVisitaE(Long idRegistroVisitaE) {
        this.idRegistroVisitaE = idRegistroVisitaE;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDocRegistroVisita() {
        return docRegistroVisita;
    }

    public void setDocRegistroVisita(String docRegistroVisita) {
        this.docRegistroVisita = docRegistroVisita;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
}
