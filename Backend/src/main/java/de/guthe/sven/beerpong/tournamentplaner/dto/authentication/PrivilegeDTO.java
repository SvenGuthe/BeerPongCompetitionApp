package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;

public class PrivilegeDTO {

    private Long id;

    private String name;

    public PrivilegeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PrivilegeDTO(Privilege privilege) {
        this.id = privilege.getId();
        this.name = privilege.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
