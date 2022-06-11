package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "tutor_empresarial")
public class TutorEmpresarial{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTutorEmpresarial;
	
	@Column(nullable = false)
	private String control;


	@ManyToOne
	@JoinColumn(name = "idPersonal", referencedColumnName = "idPersonal")
	private PersonalEmpresa personalEmpresa;

	@ManyToOne
	@JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno")
	private Alumno alumno;

	//Anexo 5
	private String docAsignacion;

	public TutorEmpresarial() {

	}

	public String getDocAsignacion() {
		return docAsignacion;
	}

	public void setDocAsignacion(String docAsignacion) {
		this.docAsignacion = docAsignacion;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Long getIdTutorEmpresarial() {
		return idTutorEmpresarial;
	}

	public void setIdTutorEmpresarial(Long idTutorEmpresarial) {
		this.idTutorEmpresarial = idTutorEmpresarial;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public PersonalEmpresa getPersonalEmpresa() {
		return personalEmpresa;
	}

	public void setPersonalEmpresa(PersonalEmpresa personalEmpresa) {
		this.personalEmpresa = personalEmpresa;
	}
	


}
