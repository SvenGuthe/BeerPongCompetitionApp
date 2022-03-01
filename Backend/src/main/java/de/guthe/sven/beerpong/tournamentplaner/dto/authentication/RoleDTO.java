package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;

public class RoleDTO {

    private Long roleId;

    private String name;

    public RoleDTO(Long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
    
    public RoleDTO(Role role) {
        this.roleId = role.getRoleId();
        this.name = role.getName();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
