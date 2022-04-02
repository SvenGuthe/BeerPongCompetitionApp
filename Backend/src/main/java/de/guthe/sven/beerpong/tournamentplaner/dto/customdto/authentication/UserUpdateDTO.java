package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.datatype.authorization.SecurityRole;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import java.util.Collection;

public class UserUpdateDTO extends ID {

    private String firstName;

    private String lastName;

    private String gamerTag;

    private String email;

    private boolean enabled;

    UserStatusType userStatusType;

    Collection<SecurityRole> roles;

    public UserUpdateDTO(Long id, String firstName, String lastName, String gamerTag, String email, boolean enabled, UserStatusType userStatusType, Collection<SecurityRole> roles) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gamerTag = gamerTag;
        this.email = email;
        this.enabled = enabled;
        this.userStatusType = userStatusType;
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserStatusType getUserStatusType() {
        return userStatusType;
    }

    public void setUserStatusType(UserStatusType userStatusType) {
        this.userStatusType = userStatusType;
    }

    public Collection<SecurityRole> getRoles() {
        return roles;
    }

    public void setRoles(Collection<SecurityRole> roles) {
        this.roles = roles;
    }

}
