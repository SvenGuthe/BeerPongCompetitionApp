package de.guthe.sven.beerpong.tournamentplaner.dto.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;

public class TeamStatusDTO {

	private Long id;

	private TeamStatusType teamStatusDescription;

	public TeamStatusDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TeamStatusType getTeamStatusDescription() {
		return teamStatusDescription;
	}

	public void setTeamStatusDescription(TeamStatusType teamStatusDescription) {
		this.teamStatusDescription = teamStatusDescription;
	}

}
