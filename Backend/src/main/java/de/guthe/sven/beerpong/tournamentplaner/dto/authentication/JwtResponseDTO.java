package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import java.io.Serializable;

public class JwtResponseDTO {

	private final String jwtToken;

	private final UserDetailDTO userDetailDTO;

	public JwtResponseDTO(String jwtToken, UserDetailDTO userDetailDTO) {
		this.jwtToken = jwtToken;
		this.userDetailDTO = userDetailDTO;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public UserDetailDTO getUserDetailDTO() {
		return userDetailDTO;
	}
}
