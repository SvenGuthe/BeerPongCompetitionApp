package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.confirmationtoken.ConfirmationTokenDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.role.RoleDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.userstatus.UserStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class UserDTO extends ID {

	@NotNull(message = "firstName in UserDTO have to be set.")
	private String firstName;

	@NotNull(message = "lastName in UserDTO have to be set.")
	private String lastName;

	@NotNull(message = "gamerTag in UserDTO have to be set.")
	private String gamerTag;

	@NotNull(message = "email in UserDTO have to be set.")
	private String email;

	@NotNull(message = "enabled in UserDTO have to be set.")
	private boolean enabled;

	@NotNull(message = "creationTime in UserDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "roles in UserDTO have to be set.")
	private Collection<RoleDTO> roles;

	@NotNull(message = "userStatus in UserDTO have to be set.")
	private Collection<UserStatusDTO> userStatus;

	@NotNull(message = "confirmationToken in UserDTO have to be set.")
	private Collection<ConfirmationTokenDTO> confirmationToken;

	// Won't be connected
	// private Collection<TeamCompositionDTO> teamCompositions;

	// Won't be connected
	// private Collection<CompositionPlayer> compositionPlayer;

	public UserDTO(Long id, String firstName, String lastName, String gamerTag, String email, boolean enabled,
			Timestamp creationTime, Collection<RoleDTO> roles, Collection<UserStatusDTO> userStatus,
			Collection<ConfirmationTokenDTO> confirmationToken) {
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
		this.roles = user.getUserRoles().stream().map(RoleDTO::new).collect(Collectors.toList());
		this.userStatus = user.getUserStatusHistories().stream().map(UserStatusDTO::new).collect(Collectors.toList());
		this.confirmationToken = user.getConfirmationTokenHistories().stream().map(ConfirmationTokenDTO::new)
				.collect(Collectors.toList());
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

	public Collection<UserStatusDTO> getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Collection<UserStatusDTO> userStatus) {
		this.userStatus = userStatus;
	}

	public Collection<ConfirmationTokenDTO> getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(Collection<ConfirmationTokenDTO> confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	@Override
	public String toString() {
		return "UserDTO{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", gamerTag='"
				+ gamerTag + '\'' + ", email='" + email + '\'' + ", enabled=" + enabled + ", creationTime="
				+ creationTime + ", roles=" + roles + ", userStatus=" + userStatus + ", confirmationToken="
				+ confirmationToken + ", id=" + id + '}';
	}

}
