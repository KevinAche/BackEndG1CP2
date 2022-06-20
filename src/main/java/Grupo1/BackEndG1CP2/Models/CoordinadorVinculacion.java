package Grupo1.BackEndG1CP2.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coordinador_vin")
public class CoordinadorVinculacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCoordinadorV;
	
	@OneToOne
	@JoinColumn(name = "idDocente", referencedColumnName = "idDocente")
	private Docente docente;
	
	
	public CoordinadorVinculacion() {
	}

	public Long getIdCoordinadorV() {
		return idCoordinadorV;
	}

	public void setIdCoordinadorV(Long idCoordinadorV) {
		this.idCoordinadorV = idCoordinadorV;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	
	
}
