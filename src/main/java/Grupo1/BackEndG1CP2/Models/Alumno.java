package Grupo1.BackEndG1CP2.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlumno;
	
	@Column(nullable = false)
	private String ciclo;
	
	@Column(nullable = false)
	private String paralelo;
	
	@OneToOne
	@JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
	private Persona persona;

	public Alumno() {
	}
	
	
	
	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	public Long getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Long idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getParalelo() {
		return paralelo;
	}

	public void setParalelo(String paralelo) {
		this.paralelo = paralelo;
	}
	
}
