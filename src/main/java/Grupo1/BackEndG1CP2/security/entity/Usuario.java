package Grupo1.BackEndG1CP2.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import Grupo1.BackEndG1CP2.Models.Persona;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String nombre;
	
	@NotNull
	@Column(unique = true)
	private String username;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
	private Persona persona;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();

	public Usuario() {
	}

	public Usuario(@NotNull String nombre, @NotNull String username, @NotNull String email, @NotNull String password) {
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
}
