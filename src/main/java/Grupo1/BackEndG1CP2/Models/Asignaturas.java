package Grupo1.BackEndG1CP2.Models;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "asignaturas")
public class Asignaturas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAsignatura;
	
	@Column(nullable = false)
	private String nombreAsignatura;

	@Column(nullable = false)
	private String abreviatura;
	
	@Column(nullable = false)
	private Integer ciclo;
	
	@ManyToOne
	@JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera")
	private Carrera carrera;


	public Asignaturas() {
	}



	public Long getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(Long idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getNombreAsignatura() {
		return nombreAsignatura;
	}

	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public Integer getCiclo() {
		return ciclo;
	}

	public void setCiclo(Integer ciclo) {
		this.ciclo = ciclo;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}


}
