package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;

public class PrivilegeDTO {

    private Long privilegeId;

    private String name;

    public PrivilegeDTO(Long privilegeId, String name) {
        this.privilegeId = privilegeId;
        this.name = name;
    }

    public PrivilegeDTO(Privilege privilege) {
        this.privilegeId = privilege.getPrivilegeId();
        this.name = privilege.getName();
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
