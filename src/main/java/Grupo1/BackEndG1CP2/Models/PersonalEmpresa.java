package Grupo1.BackEndG1CP2.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "personal_empresa")
public class PersonalEmpresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonal;
	
	@Column(nullable = false)
	private String cargo;
	
	@Column(nullable = false)
	private double sueldo;
	
	@ManyToOne
	@JoinColumn(name = "idEmpresa", referencedColumnName = "idEmpresa")
	private Empresa empresa;
	
	@OneToOne
	@JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
	private Persona persona;
	
	@OneToOne(mappedBy = "personalEmpresa")
	private TutorEmpresarial tutorEmpresarial;
	
	@OneToMany(mappedBy = "empleado")
	private List<SolicitudEmpresa> solicitudEmpresa;

	public PersonalEmpresa() {
		super();
	}

	public Long getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(Long idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TutorEmpresarial getTutorEmpresarial() {
		return tutorEmpresarial;
	}

	public void setTutorEmpresarial(TutorEmpresarial tutorEmpresarial) {
		this.tutorEmpresarial = tutorEmpresarial;
	}

	public List<SolicitudEmpresa> getSolicitudEmpresa() {
		return solicitudEmpresa;
	}

	public void setSolicitudEmpresa(List<SolicitudEmpresa> solicitudEmpresa) {
		this.solicitudEmpresa = solicitudEmpresa;
	}

	
}
