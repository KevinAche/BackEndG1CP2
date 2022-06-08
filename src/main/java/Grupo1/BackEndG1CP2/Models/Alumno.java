package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.List;

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

	@Column(nullable = false)
	private double promedio;

	@OneToOne
	@JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera")
	private Carrera carrera;

	@OneToMany(mappedBy = "alumno")
	private List<SolicitudAlumno> solicitudAlumnoList;

	@OneToOne(mappedBy = "alumno")
	private TutorAcademico tutorAcademico;

	@OneToOne(mappedBy = "alumno")
	private TutorEmpresarial tutorEmpresarial;

	public Alumno() {
	}



	public List<SolicitudAlumno> getSolicitudAlumnoList() {
		return solicitudAlumnoList;
	}

	public void setSolicitudAlumnoList(List<SolicitudAlumno> solicitudAlumnoList) {
		this.solicitudAlumnoList = solicitudAlumnoList;
	}

	public TutorAcademico getTutorAcademico() {
		return tutorAcademico;
	}

	public void setTutorAcademico(TutorAcademico tutorAcademico) {
		this.tutorAcademico = tutorAcademico;
	}

	public TutorEmpresarial getTutorEmpresarial() {
		return tutorEmpresarial;
	}

	public void setTutorEmpresarial(TutorEmpresarial tutorEmpresarial) {
		this.tutorEmpresarial = tutorEmpresarial;
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

	public double getPromedio() {
		return promedio;
	}

	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}
}
