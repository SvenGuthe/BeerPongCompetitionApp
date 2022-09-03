package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class JwtRequestDTO implements Serializable {

	@NotNull(message = "username in JwtRequestDTO have to be set.")
	private String username;

	@NotNull(message = "password in JwtRequestDTO have to be set.")
	private String password;

	public JwtRequestDTO() {

	}

	public JwtRequestDTO(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequestDTO{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
	}

}
