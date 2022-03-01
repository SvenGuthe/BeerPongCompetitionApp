package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtResponseDTO implements Serializable {

	private final String jwtToken;

	private final Collection<RoleDTO> roles;

	private final Collection<PrivilegeDTO> privileges;

	public JwtResponseDTO(String jwtToken, Collection<Role> roles, Collection<Privilege> privileges) {
		this.jwtToken = jwtToken;
		this.roles = roles.stream().map(RoleDTO::new).collect(Collectors.toList());
		this.privileges = privileges.stream().map(PrivilegeDTO::new).collect(Collectors.toList());
	}

	public String getToken() {
		return this.jwtToken;
	}

	public Collection<RoleDTO> getRoles() {
		return roles;
	}

	public Collection<PrivilegeDTO> getPrivileges() {
		return privileges;
	}
}
