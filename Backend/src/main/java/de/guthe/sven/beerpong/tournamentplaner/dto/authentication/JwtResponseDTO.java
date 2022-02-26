package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import java.io.Serializable;

public class JwtResponseDTO implements Serializable {

	private final String jwtToken;

	public JwtResponseDTO(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getToken() {
		return this.jwtToken;
	}

}
