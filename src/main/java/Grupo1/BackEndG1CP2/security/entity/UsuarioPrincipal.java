package Grupo1.BackEndG1CP2.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Grupo1.BackEndG1CP2.Models.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {

	private String nombre;

	private String username;

	private String email;

	private String password;

	private Persona persona;

	private Collection<? extends GrantedAuthority> authorities;

	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getNombre(), usuario.getUsername(), usuario.getEmail(),
				usuario.getPassword(),usuario.getPersona(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public UsuarioPrincipal() {
	}

	public UsuarioPrincipal(String nombre, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public UsuarioPrincipal(String nombre, String username, String email, String password,
							Persona persona, Collection<? extends GrantedAuthority> authorities) {
		this.nombre = nombre;
		this.username = username;
		this.email = email;
		this.password = password;
		this.persona = persona;
		this.authorities = authorities;
	}
}
