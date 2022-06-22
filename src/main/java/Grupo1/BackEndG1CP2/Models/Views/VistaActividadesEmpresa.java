package Grupo1.BackEndG1CP2.Models.Views;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Immutable;

@Entity
@Table(name = "lista_actividades_empresa")
@Immutable
public class VistaActividadesEmpresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_actividad;
	
	private String descripcion;
	
	private String area;
	
	private Long id_empresa;
	
	private String nombre_empresa;
	
	private String nombresrp;
	
	private String apellidosrp;
	
	private String titulo;
	
	private String abrev_titulo;

	public Long getId_actividad() {
		return id_actividad;
	}

	public void setId_actividad(Long id_actividad) {
		this.id_actividad = id_actividad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getNombresrp() {
		return nombresrp;
	}

	public void setNombresrp(String nombresrp) {
		this.nombresrp = nombresrp;
	}

	public String getApellidosrp() {
		return apellidosrp;
	}

	public void setApellidosrp(String apellidosrp) {
		this.apellidosrp = apellidosrp;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAbrev_titulo() {
		return abrev_titulo;
	}

	public void setAbrev_titulo(String abrev_titulo) {
		this.abrev_titulo = abrev_titulo;
	}
	

}
