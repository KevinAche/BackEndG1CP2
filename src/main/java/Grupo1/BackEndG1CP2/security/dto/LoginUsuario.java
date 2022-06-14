package Grupo1.BackEndG1CP2.security.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginUsuario {
	
	
	private String username;
	
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
