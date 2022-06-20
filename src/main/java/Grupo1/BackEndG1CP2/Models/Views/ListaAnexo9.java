package Grupo1.BackEndG1CP2.Models.Views;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

@Entity
@Table(name = "lista_anexo9")
@Immutable
public class ListaAnexo9 {
	
	@Id
	private Long id_alumno;

	private Long id_registro_asistencia;

	private String cedula_a;
	
	private String nombre_a;
	
	private String apellido_a;
	
	private Long id_carrera;
	
	private String nombre_carrera;
	
	private Long id_solicitud_alumno;
	
	private String estado;
	
	private Long id_tutor_empresarial;
	
	private String nombre_t;
	
	private String apellido_t;
	
	private Long id_empresa;
	
	private String nombre_empresa;

	public Long getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(Long id_alumno) {
		this.id_alumno = id_alumno;
	}

	public String getCedula_a() {
		return cedula_a;
	}

	public void setCedula_a(String cedula_a) {
		this.cedula_a = cedula_a;
	}

	public String getNombre_a() {
		return nombre_a;
	}

	public void setNombre_a(String nombre_a) {
		this.nombre_a = nombre_a;
	}

	public String getApellido_a() {
		return apellido_a;
	}

	public void setApellido_a(String apellido_a) {
		this.apellido_a = apellido_a;
	}

	public Long getId_carrera() {
		return id_carrera;
	}

	public void setId_carrera(Long id_carrera) {
		this.id_carrera = id_carrera;
	}

	public String getNombre_carrera() {
		return nombre_carrera;
	}

	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}

	public Long getId_solicitud_alumno() {
		return id_solicitud_alumno;
	}

	public void setId_solicitud_alumno(Long id_solicitud_alumno) {
		this.id_solicitud_alumno = id_solicitud_alumno;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId_tutor_empresarial() {
		return id_tutor_empresarial;
	}

	public void setId_tutor_empresarial(Long id_tutor_empresarial) {
		this.id_tutor_empresarial = id_tutor_empresarial;
	}

	public String getNombre_t() {
		return nombre_t;
	}

	public void setNombre_t(String nombre_t) {
		this.nombre_t = nombre_t;
	}

	public String getApellido_t() {
		return apellido_t;
	}

	public void setApellido_t(String apellido_t) {
		this.apellido_t = apellido_t;
	}

	public Long getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Long id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public Long getId_registro_asistencia() { return id_registro_asistencia;	}

	public void setId_registro_asistencia(Long id_registro_asistencia) { this.id_registro_asistencia = id_registro_asistencia;	}
}
