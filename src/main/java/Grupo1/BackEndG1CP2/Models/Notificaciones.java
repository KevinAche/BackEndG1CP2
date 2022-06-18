package Grupo1.BackEndG1CP2.Models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "notificaciones")
public class Notificaciones {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNotificacion;
	
	private String notificacion;
	
	private Date fechaNotificacion;
	
	@ManyToOne
	@JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
	private Persona persona;

	@PrePersist
	public void fecha(){
		this.fechaNotificacion=new Date();
	}

	public Notificaciones() {
		super();
	}

	public Long getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public String getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}

	public Date getFechaNotificacion() {
		return fechaNotificacion;
	}

	public void setFechaNotificacion(Date fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
	
	

}
