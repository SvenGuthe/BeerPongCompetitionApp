package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class RoleDTO extends EnumDTO {

    @NotNull(message = "role in RoleDTO have to be set.")
    private SecurityRole role;

    @NotNull(message = "privileges in RoleDTO have to be set.")
    private Collection<PrivilegeDTO> privileges;

    public RoleDTO(Long id, SecurityRole role, Collection<PrivilegeDTO> privileges) {
        super(id, role.name());
        this.role = role;
        this.privileges = privileges;
    }

    public RoleDTO(Role role) {
        super(role.getId(), role.getRole().name());
        this.role = role.getRole();
        this.privileges = role.getPrivileges().stream().map(PrivilegeDTO::new).collect(Collectors.toList());
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
}
