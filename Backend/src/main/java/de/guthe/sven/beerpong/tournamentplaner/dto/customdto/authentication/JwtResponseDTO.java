package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;

import javax.validation.constraints.NotNull;

public class JwtResponseDTO {

	@NotNull(message = "jwtToken in JwtResponseDTO have to be set.")
	private final String jwtToken;

	@NotNull(message = "userDTO in JwtResponseDTO have to be set.")
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
