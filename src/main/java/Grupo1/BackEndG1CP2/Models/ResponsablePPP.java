package Grupo1.BackEndG1CP2.Models;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "responsable_ppp")
public class ResponsablePPP{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idResponsablePPP;
	
	@OneToOne
	@JoinColumn(name = "idDocente", referencedColumnName = "idDocente")
	private Docente docente;

	public ResponsablePPP() {
		super();
	}
	
	
	
	public Docente getDocente() {
		return docente;
	}



	public void setDocente(Docente docente) {
		this.docente = docente;
	}



	public Long getIdResponsablePPP() {
		return idResponsablePPP;
	}

	public void setIdResponsablePPP(Long idResponsablePPP) {
		this.idResponsablePPP = idResponsablePPP;
	}

	
}
