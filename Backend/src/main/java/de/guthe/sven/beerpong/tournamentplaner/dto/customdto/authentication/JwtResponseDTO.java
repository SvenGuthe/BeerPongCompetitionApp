package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;

public class JwtResponseDTO {

	private final String jwtToken;

	private final UserDTO userDTO;

	public JwtResponseDTO(String jwtToken, UserDTO userDTO) {
		this.jwtToken = jwtToken;
		this.userDTO = userDTO;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}
}
