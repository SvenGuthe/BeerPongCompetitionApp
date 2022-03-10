package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityPrivilege;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;

public class PrivilegeDTO extends EnumDTO {

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
