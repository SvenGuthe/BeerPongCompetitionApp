package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.teamstatus;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = Team ID
public class TeamStatusUpdateDTO extends ID {

	@NotNull(message = "teamStatusType in TeamStatusUpdateDTO have to be set.")
	private TeamStatusType teamStatusType;

	public TeamStatusUpdateDTO(Long id, TeamStatusType teamStatusType) {
		super(id);
		this.teamStatusType = teamStatusType;
	}

	public TeamStatusType getTeamStatusType() {
		return teamStatusType;
	}

	public void setTeamStatusType(TeamStatusType teamStatusType) {
		this.teamStatusType = teamStatusType;
	}

	@Override
	public String toString() {
		return "TeamStatusUpdateDTO{" + "teamStatusType=" + teamStatusType + ", id=" + id + '}';
	}

}
