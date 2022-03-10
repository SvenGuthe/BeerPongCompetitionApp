package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

public class ChangeUserRoleDTO {

    private Long id;

    private Boolean value;

    private String role;

    public ChangeUserRoleDTO(Long id, Boolean value, String role) {
        this.id = id;
        this.value = value;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
