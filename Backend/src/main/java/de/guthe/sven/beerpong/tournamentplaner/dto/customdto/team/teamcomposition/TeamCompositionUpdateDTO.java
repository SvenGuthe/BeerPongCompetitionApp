package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.teamcomposition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = TeamComposition ID
public class TeamCompositionUpdateDTO extends ID {

	@NotNull(message = "isAdmin in TeamCompositionUpdateDTO have to be set.")
	private Boolean isAdmin;

	public TeamCompositionUpdateDTO(Long id, Boolean isAdmin) {
		super(id);
		this.isAdmin = isAdmin;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	@Override
	public String toString() {
		return "TeamCompositionUpdateDTO{" + "isAdmin=" + isAdmin + ", id=" + id + '}';
	}

}
