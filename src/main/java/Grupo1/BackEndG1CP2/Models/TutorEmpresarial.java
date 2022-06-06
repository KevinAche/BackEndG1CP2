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
	
	@OneToOne
	@JoinColumn(name = "idPersonal", referencedColumnName = "idPersonal")
	private PersonalEmpresa personalEmpresa;

	public TutorEmpresarial() {
		super();
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
