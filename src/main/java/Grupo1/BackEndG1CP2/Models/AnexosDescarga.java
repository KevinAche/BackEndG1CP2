package Grupo1.BackEndG1CP2.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anexos_descarga")
public class AnexosDescarga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAnexo;
	
	private String nombre;
	
	private String archivo;

	public AnexosDescarga() {
	}

	public Long getIdAnexo() {
		return idAnexo;
	}

	public void setIdAnexo(Long idAnexo) {
		this.idAnexo = idAnexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	
	

}
