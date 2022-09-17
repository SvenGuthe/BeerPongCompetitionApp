package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = CompetitionTeam ID
public class CompetitionPlayerStatusUpdateDTO extends ID {

	@NotNull(message = "competitionPlayerStatusType in CompetitionPlayerStatusUpdateDTO have to be set.")
	CompetitionPlayerStatusType competitionPlayerStatusType;

	public CompetitionPlayerStatusUpdateDTO(Long id, CompetitionPlayerStatusType competitionPlayerStatusType) {
		super(id);
		this.competitionPlayerStatusType = competitionPlayerStatusType;
	}

	public CompetitionPlayerStatusType getCompetitionPlayerStatusType() {
		return competitionPlayerStatusType;
	}

	public void setCompetitionPlayerStatusType(CompetitionPlayerStatusType competitionPlayerStatusType) {
		this.competitionPlayerStatusType = competitionPlayerStatusType;
	}

	@Override
	public String toString() {
		return "CompetitionPlayerStatusUpdateDTO{" + "competitionPlayerStatusType=" + competitionPlayerStatusType
				+ ", id=" + id + '}';
	}

}
