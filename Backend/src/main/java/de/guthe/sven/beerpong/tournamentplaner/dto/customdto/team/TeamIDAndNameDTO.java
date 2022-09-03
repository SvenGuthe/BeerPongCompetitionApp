package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import javax.validation.constraints.NotNull;

public class TeamIDAndNameDTO extends ID {

	@NotNull(message = "teamName in TeamIDAndNameDTO have to be set.")
	private String teamName;

	public TeamIDAndNameDTO(Long id, String teamName) {
		super(id);
		this.teamName = teamName;
	}

	public TeamIDAndNameDTO(Team team) {
		super(team.getId());
		this.teamName = team.getTeamName();
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "TeamIDAndNameDTO{" + "teamName='" + teamName + '\'' + ", id=" + id + '}';
	}

}
