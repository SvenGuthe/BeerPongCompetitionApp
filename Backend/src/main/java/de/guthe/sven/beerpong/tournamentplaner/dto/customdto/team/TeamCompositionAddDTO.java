package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = Team ID
public class TeamCompositionAddDTO extends ID {

	@NotNull(message = "userId in TeamCompositionAddDTO have to be set.")
	private Long userId;

	@NotNull(message = "isAdmin in TeamCompositionAddDTO have to be set.")
	private Boolean isAdmin;

	public TeamCompositionAddDTO(Long id, Long userId, Boolean isAdmin) {
		super(id);
		this.userId = userId;
		this.isAdmin = isAdmin;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

}
