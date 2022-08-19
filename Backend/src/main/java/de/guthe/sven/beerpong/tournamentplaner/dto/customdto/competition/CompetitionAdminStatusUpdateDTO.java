package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

public class CompetitionAdminStatusUpdateDTO extends ID {

	@NotNull(message = "competitionAdminStatusType in CompetitionAdminStatusUpdateDTO have to be set.")
	private CompetitionAdminStatusType competitionAdminStatusType;

	public CompetitionAdminStatusUpdateDTO(Long id, CompetitionAdminStatusType competitionAdminStatusType) {
		super(id);
		this.competitionAdminStatusType = competitionAdminStatusType;
	}

	public CompetitionAdminStatusType getCompetitionAdminStatusType() {
		return competitionAdminStatusType;
	}

	public void setCompetitionAdminStatusType(CompetitionAdminStatusType competitionAdminStatusType) {
		this.competitionAdminStatusType = competitionAdminStatusType;
	}

}
