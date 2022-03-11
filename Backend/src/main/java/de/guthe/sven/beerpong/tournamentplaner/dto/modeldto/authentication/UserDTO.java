package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserDTO extends ID {

    private String firstName;

    private String lastName;

    private String gamerTag;

    private String email;

    private boolean enabled;

    private Timestamp creationTime;

    Collection<RoleDTO> roles;

    UserStatusDTO userStatus;

    Collection<ConfirmationTokenDTO> confirmationToken;

    // Won't be connected
    // private Collection<TeamCompositionDTO> teamCompositions;

    // Won't be connected
    // private Collection<CompositionPlayer>  compositionPlayer;


    public UserDTO(Long id, String firstName, String lastName, String gamerTag, String email, boolean enabled, Timestamp creationTime, Collection<RoleDTO> roles, UserStatusDTO userStatus, Collection<ConfirmationTokenDTO> confirmationToken) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gamerTag = gamerTag;
        this.email = email;
        this.enabled = enabled;
        this.creationTime = creationTime;
        this.roles = roles;
        this.userStatus = userStatus;
        this.confirmationToken = confirmationToken;
    }

    public UserDTO(User user) {
        super(user.getId());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.gamerTag = user.getGamerTag();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.creationTime = user.getCreationTime();
        this.roles = user.getRoles().stream().map(RoleDTO::new).collect(Collectors.toList());
        this.userStatus = new UserStatusDTO(user.getUserStatus());
        this.confirmationToken = user.getConfirmationToken().stream().map(ConfirmationTokenDTO::new).collect(Collectors.toList());
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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Collection<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleDTO> roles) {
        this.roles = roles;
    }

    public UserStatusDTO getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatusDTO userStatus) {
        this.userStatus = userStatus;
    }

    public Collection<ConfirmationTokenDTO> getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(Collection<ConfirmationTokenDTO> confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
}
