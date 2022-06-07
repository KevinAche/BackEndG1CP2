package Grupo1.BackEndG1CP2.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "asignaturas")
public class Asignaturas {
	
	private Long idAsignatura;
	
	private String nombreAsignatura;
	
	private Integer ciclo;
	
	@ManyToOne
	@JoinColumn(name = "idCarrera", referencedColumnName = "idCarrera")
	private Carrera carrera;
	
	@OneToMany(mappedBy = "asignatura")
	private List<Actividades> actividades;
	

}
