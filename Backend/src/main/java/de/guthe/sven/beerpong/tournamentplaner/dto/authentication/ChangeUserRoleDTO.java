package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

public class ChangeUserRoleDTO {

    private Long userId;

    private Boolean value;

    private String role;

    public ChangeUserRoleDTO(Long userId, Boolean value, String role) {
        this.userId = userId;
        this.value = value;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
