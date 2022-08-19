package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = Team ID
public class TeamUpdateDTO extends ID {

	@NotNull(message = "teamName in TeamUpdateDTO have to be set.")
	private String teamName;

	public TeamUpdateDTO(Long id, String teamName) {
		super(id);
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
