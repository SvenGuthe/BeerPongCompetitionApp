package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityPrivilege;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;

import javax.validation.constraints.NotNull;

public class PrivilegeDTO extends EnumDTO {

    @NotNull(message = "privilege in PrivilegeDTO have to be set.")
    private SecurityPrivilege privilege;

    public PrivilegeDTO(Long id, SecurityPrivilege privilege) {
        super(id, privilege.name());
        this.privilege = privilege;
    }

    public PrivilegeDTO(Privilege privilege) {
        super(privilege.getId(), privilege.getPrivilege().name());
        this.privilege = privilege.getPrivilege();
    }

    public SecurityPrivilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(SecurityPrivilege privilege) {
        this.privilege = privilege;
    }
}
