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

	
	@ManyToOne
	@JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera")
	private Carrera carrera;

	private boolean coordinador = false;

	public Docente() {
	}
	
	
	
	public Carrera getCarrera() {
		return carrera;
	}



	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}



	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
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

	public boolean isCoordinador() {
		return coordinador;
	}

	public void setCoordinador(boolean coordinador) {
		this.coordinador = coordinador;
	}
}
