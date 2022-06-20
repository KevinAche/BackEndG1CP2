package Grupo1.BackEndG1CP2.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "convenios")
public class Convenio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConvenio;
	
	@Column(nullable = false)
	private Date fechaEmision;
	
	@Column(nullable = false)
	private int duracion;
	
	@Column(columnDefinition="text", length=10485760)
	private String documento;
	
	@OneToOne
	@JoinColumn(name = "idGerente", referencedColumnName = "idPersonal")
	private PersonalEmpresa gerente;
	
	@OneToOne
	@JoinColumn(name = "idResponsablePPP", referencedColumnName = "idResponsablePPP")
	private ResponsablePPP responsablePPP;
	
	

	public Convenio() {
	}

	public Long getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public PersonalEmpresa getGerente() {
		return gerente;
	}

	public void setGerente(PersonalEmpresa gerente) {
		this.gerente = gerente;
	}

	public ResponsablePPP getResponsablePPP() {
		return responsablePPP;
	}

	public void setResponsablePPP(ResponsablePPP responsablePPP) {
		this.responsablePPP = responsablePPP;
	}
	

}
