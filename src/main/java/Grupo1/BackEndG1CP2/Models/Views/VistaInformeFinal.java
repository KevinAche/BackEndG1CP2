package Grupo1.BackEndG1CP2.Models.Views;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

@Entity
@Table(name = "informes_finales_estudiantes")
@Immutable
public class VistaInformeFinal {

	@Id
	private Long id_informe_final;

	private Date fecha_emision;

	private String estado;

	private String id_alumno;

	private String a_nombres;

	private String a_apellidos;
	
	private String cedula;
	
	

	public VistaInformeFinal() {
		super();
	}

	public Long getId_informe_final() {
		return id_informe_final;
	}

	public void setId_informe_final(Long id_informe_final) {
		this.id_informe_final = id_informe_final;
	}

	public Date getFecha_emision() {
		return fecha_emision;
	}

	public void setFecha_emision(Date fecha_emision) {
		this.fecha_emision = fecha_emision;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getA_nombres() {
		return a_nombres;
	}

	public void setA_nombres(String a_nombres) {
		this.a_nombres = a_nombres;
	}

	public String getA_apellidos() {
		return a_apellidos;
	}

	public void setA_apellidos(String a_apellidos) {
		this.a_apellidos = a_apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	

}
