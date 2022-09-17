package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.teamcomposition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamCompositionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = TeamComposition ID
public class TeamCompositionStatusUpdateDTO extends ID {

	@NotNull(message = "teamCompositionStatusType in TeamCompositionStatusUpdateDTO have to be set.")
	private TeamCompositionStatusType teamCompositionStatusType;

	public TeamCompositionStatusUpdateDTO(Long id, TeamCompositionStatusType teamCompositionStatusType) {
		super(id);
		this.teamCompositionStatusType = teamCompositionStatusType;
	}

	public TeamCompositionStatusType getTeamCompositionStatusType() {
		return teamCompositionStatusType;
	}

	public void setTeamCompositionStatusType(TeamCompositionStatusType teamCompositionStatusType) {
		this.teamCompositionStatusType = teamCompositionStatusType;
	}

	@Override
	public String toString() {
		return "TeamCompositionStatusUpdateDTO{" + "teamCompositionStatusType=" + teamCompositionStatusType + ", id="
				+ id + '}';
	}

}
