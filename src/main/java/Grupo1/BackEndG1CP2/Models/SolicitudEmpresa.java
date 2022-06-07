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
	
	@Column(nullable = false)
	private String pdfSolicitud;
	
	// Anexos 3.1 y 4 
	@Column(nullable = false)
	private String respuesta;
	
	@Column(nullable = false)
	private boolean estado;
	
	@OneToMany(mappedBy = "solicitudEmpresa")
	private List<Actividades> actividades;
	
	@ManyToOne
	@JoinColumn(name ="idEmpleado", referencedColumnName = "idPersonal")
	private PersonalEmpresa empleado;
	
	@ManyToOne
	@JoinColumn(name = "idResponsable", referencedColumnName = "idResponsable")
	private ResponsablePPP responsablePPP;
	
	
	
	
	
}
