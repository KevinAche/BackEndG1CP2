package Grupo1.BackEndG1CP2.Models;

import java.util.Date;
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

@Entity
@Table(name = "solicitudes_empresa")
public class SolicitudEmpresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSolicitudEmpresa;

	@Column(nullable = false)
	private Date fechaEmision;

	@Column(nullable = false)
	private Date fechaInicio;

	@Column(nullable = false)
	private Integer numeroAlumnos;

	//Anexo 1
	@Column(columnDefinition = "text",length = 10485760)
	private String pdfSolicitud;
	
	// Anexos 3.1 y 4 
	@Column(columnDefinition = "text",length = 10485760)
	private String respuesta;
	
	@Column(nullable = false)
	private boolean estado;
	
	@OneToMany(mappedBy = "solicitudEmpresa")
	private List<Actividades> actividades;
	
	@ManyToOne
	@JoinColumn(name ="idEmpleado", referencedColumnName = "idPersonal")
	private PersonalEmpresa empleado;
	
	@ManyToOne
	@JoinColumn(name = "idResponsablePPP", referencedColumnName = "idResponsablePPP")
	private ResponsablePPP responsablePPP;

	@ManyToOne
	@JoinColumn(name = "idCarrera",referencedColumnName = "idCarrera")
	private Carrera carrera;

	@OneToOne(mappedBy = "solicitudEmpresa")
	private Convocatoria convocatoria;

	public SolicitudEmpresa() {
	}

	public Long getIdSolicitudEmpresa() {
		return idSolicitudEmpresa;
	}

	public void setIdSolicitudEmpresa(Long idSolicitudEmpresa) {
		this.idSolicitudEmpresa = idSolicitudEmpresa;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getNumeroAlumnos() {
		return numeroAlumnos;
	}

	public void setNumeroAlumnos(Integer numeroAlumnos) {
		this.numeroAlumnos = numeroAlumnos;
	}

	public String getPdfSolicitud() {
		return pdfSolicitud;
	}

	public void setPdfSolicitud(String pdfSolicitud) {
		this.pdfSolicitud = pdfSolicitud;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Actividades> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividades> actividades) {
		this.actividades = actividades;
	}

	public PersonalEmpresa getEmpleado() {
		return empleado;
	}

	public void setEmpleado(PersonalEmpresa empleado) {
		this.empleado = empleado;
	}

	public ResponsablePPP getResponsablePPP() {
		return responsablePPP;
	}

	public void setResponsablePPP(ResponsablePPP responsablePPP) {
		this.responsablePPP = responsablePPP;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}
}
