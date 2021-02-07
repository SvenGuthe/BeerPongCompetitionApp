package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;

import java.util.Set;

public class UserInformationDTO {

	private Long userId;

	private String gamerTag;

	private Set<Privilege> privileges;

	public UserInformationDTO() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getGamerTag() {
		return gamerTag;
	}

	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
