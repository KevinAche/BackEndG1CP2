package Grupo1.BackEndG1CP2.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acreditacion")
public class Acreditacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAcreditacion;
	
	@OneToOne
	@JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno")
	private Alumno alumno;
	
	@ManyToOne
	@JoinColumn(name = "idCoordinadorV", referencedColumnName = "idCoordinadorV")
	private CoordinadorVinculacion vinculacion;
	
	@Column(columnDefinition="text", length=10485760)
	private String documento;

	public Acreditacion() {
	}

	public Long getIdAcreditacion() {
		return idAcreditacion;
	}

	public void setIdAcreditacion(Long idAcreditacion) {
		this.idAcreditacion = idAcreditacion;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public CoordinadorVinculacion getVinculacion() {
		return vinculacion;
	}

	public void setVinculacion(CoordinadorVinculacion vinculacion) {
		this.vinculacion = vinculacion;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
}
