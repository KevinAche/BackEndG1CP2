package Grupo1.BackEndG1CP2.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtDto {
	
	private String token;
	
	private String bearer = "Bearer";
	
	private String username;

	private String nameUser;
	
	private Collection<? extends GrantedAuthority> authorities;

	public JwtDto(String token, String username, String nameUser, Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.username = username;
		this.nameUser = nameUser;
		this.authorities = authorities;
	}

	public JwtDto(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = token;
		this.username = username;
		this.authorities = authorities;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
}
