package Grupo1.BackEndG1CP2.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "carreras")
public class Carrera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCarrera;
	
	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String abreviatura;
	
	@Column(nullable = false)
	private String modalidad;
	
	@OneToMany(mappedBy = "carrera")
	private List<Docente> docente;
	
	@OneToMany(mappedBy = "carrera")
	private List<Alumno> alumno;
	
	@OneToMany(mappedBy = "carrera")
	private List<Asignaturas> asignaturas;

	public Carrera() {
		super();
	}

	public Long getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public List<Docente> getDocente() {
		return docente;
	}

	public void setDocente(List<Docente> docente) {
		this.docente = docente;
	}

	public List<Alumno> getAlumno() {
		return alumno;
	}

	public void setAlumno(List<Alumno> alumno) {
		this.alumno = alumno;
	}
	
	

}
