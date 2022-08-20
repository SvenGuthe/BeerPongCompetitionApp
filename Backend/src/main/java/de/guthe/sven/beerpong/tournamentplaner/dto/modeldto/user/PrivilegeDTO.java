package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user;

import de.guthe.sven.beerpong.tournamentplaner.datatype.user.SecurityPrivilege;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.RolePrivilege;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class PrivilegeDTO extends EnumDTO {

	@NotNull(message = "privilege in PrivilegeDTO have to be set.")
	private SecurityPrivilege privilege;

	@NotNull(message = "validFrom in TeamStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	public PrivilegeDTO(Long id, SecurityPrivilege privilege, Timestamp validFrom, Timestamp validTo) {
		super(id, privilege.name());
		this.privilege = privilege;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public PrivilegeDTO(RolePrivilege rolePrivilege) {
		super(rolePrivilege.getId(), rolePrivilege.getPrivilege().getPrivilege().name());
		this.privilege = rolePrivilege.getPrivilege().getPrivilege();
		this.validFrom = rolePrivilege.getValidFrom();
		this.validTo = rolePrivilege.getValidTo();
	}

	public SecurityPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(SecurityPrivilege privilege) {
		this.privilege = privilege;
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
