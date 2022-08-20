package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user;

import de.guthe.sven.beerpong.tournamentplaner.datatype.user.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.UserRole;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class RoleDTO extends EnumDTO {

	@NotNull(message = "role in RoleDTO have to be set.")
	private SecurityRole role;

	@NotNull(message = "validFrom in TeamStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	@NotNull(message = "privileges in RoleDTO have to be set.")
	private Collection<PrivilegeDTO> privileges;

	public RoleDTO(Long id, SecurityRole role, Collection<PrivilegeDTO> privileges, Timestamp validFrom,
			Timestamp validTo) {
		super(id, role.name());
		this.role = role;
		this.privileges = privileges;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public RoleDTO(UserRole userRole) {
		super(userRole.getId(), userRole.getRole().getRole().name());
		this.role = userRole.getRole().getRole();
		this.privileges = userRole.getRole().getRolePrivileges().stream().map(PrivilegeDTO::new)
				.collect(Collectors.toList());
	}

	public SecurityRole getRole() {
		return role;
	}

	public void setRole(SecurityRole role) {
		this.role = role;
	}

	public Collection<PrivilegeDTO> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<PrivilegeDTO> privileges) {
		this.privileges = privileges;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

}
