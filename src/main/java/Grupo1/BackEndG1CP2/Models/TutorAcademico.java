package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table (name = "tutor_academico")
public class TutorAcademico{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTutorAcademico;
	
	@ManyToOne
	@JoinColumn(name = "idDocente", referencedColumnName = "idDocente")
	private Docente docente;

	@ManyToOne
	@JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno")
	private Alumno alumno;

	//Anexo 6
	@Column(length = 10485760)
	private String docAsignacion;
	
	public TutorAcademico() {

	}


	public String getDocAsignacion() {
		return docAsignacion;
	}

	public void setDocAsignacion(String docAsignacion) {
		this.docAsignacion = docAsignacion;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Long getIdTutorAcademico() {
		return idTutorAcademico;
	}

	public void setIdTutorAcademico(Long idTutorAcademico) {
		this.idTutorAcademico = idTutorAcademico;
	}

}
