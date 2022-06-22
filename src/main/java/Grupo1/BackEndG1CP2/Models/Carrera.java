package Grupo1.BackEndG1CP2.Models;

import java.util.List;

import javax.persistence.*;

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
	
	@Column(nullable = false)
	private String duracion;


	public Carrera() {

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


	public String getDuracion() {
		return duracion;
	}


	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

}
