package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "certificadoA12_1")
public class CertificadoEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCertificadoEmpresa;

    private Date fechaEmision;

    //Anexo12.1
    private String docCertificadoE;

    @ManyToOne
    @JoinColumn(name = "idTutorEmpresarial",referencedColumnName = "idTutorEmpresarial")
    private TutorEmpresarial tutorEmpresarial;

    public CertificadoEmpresa() {
    }

    public Long getIdCertificadoEmpresa() {
        return idCertificadoEmpresa;
    }

    public void setIdCertificadoEmpresa(Long idCertificadoEmpresa) {
        this.idCertificadoEmpresa = idCertificadoEmpresa;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDocCertificadoE() {
        return docCertificadoE;
    }

    public void setDocCertificadoE(String docCertificadoE) {
        this.docCertificadoE = docCertificadoE;
    }

    public TutorEmpresarial getTutorEmpresarial() {
        return tutorEmpresarial;
    }

    public void setTutorEmpresarial(TutorEmpresarial tutorEmpresarial) {
        this.tutorEmpresarial = tutorEmpresarial;
    }
}
