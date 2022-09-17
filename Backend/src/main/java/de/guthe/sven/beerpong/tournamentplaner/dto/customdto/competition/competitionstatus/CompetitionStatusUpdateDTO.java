package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

public class CompetitionStatusUpdateDTO extends ID {

	@NotNull(message = "competitionStatusType in CompetitionStatusType have to be set.")
	private CompetitionStatusType competitionStatusType;

	public CompetitionStatusUpdateDTO(Long id, CompetitionStatusType competitionStatusType) {
		super(id);
		this.competitionStatusType = competitionStatusType;
	}

	public CompetitionStatusType getCompetitionStatusType() {
		return competitionStatusType;
	}

	public void setCompetitionStatusType(CompetitionStatusType competitionStatusType) {
		this.competitionStatusType = competitionStatusType;
	}

	@Override
	public String toString() {
		return "CompetitionStatusUpdateDTO{" + "competitionStatusType=" + competitionStatusType + ", id=" + id + '}';
	}

}
