package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "docente")
public class Docente{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDocente;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private String area;
	
	@Column(nullable = false)
	private String abrevTitulo;
	
	@OneToOne
	@JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
	private Persona persona;
	
	@OneToOne(mappedBy = "docente")
	private ResponsablePPP responsablePPP;
	
	@OneToOne(mappedBy = "docente")
	private TutorAcademico tutorAcademico;

	public Docente() {
	}
	
	
	
	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	public ResponsablePPP getResponsablePPP() {
		return responsablePPP;
	}



	public void setResponsablePPP(ResponsablePPP responsablePPP) {
		this.responsablePPP = responsablePPP;
	}



	public TutorAcademico getTutorAcademico() {
		return tutorAcademico;
	}



	public void setTutorAcademico(TutorAcademico tutorAcademico) {
		this.tutorAcademico = tutorAcademico;
	}



	public Long getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(Long idDocente) {
		this.idDocente = idDocente;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAbrevTitulo() {
		return abrevTitulo;
	}

	public void setAbrevTitulo(String abrevTitulo) {
		this.abrevTitulo = abrevTitulo;
	}
}
