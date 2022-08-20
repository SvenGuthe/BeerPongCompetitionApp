package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatusHistory;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CompetitionPlayerStatusDTO extends EnumDTO {

	@NotNull(message = "competitionPlayerStatusDescription in CompetitionPlayerStatusDTO have to be set.")
	private CompetitionPlayerStatusType competitionPlayerStatusDescription;

	@NotNull(message = "creationTime in CompetitionPlayerStatusDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "validFrom in CompetitionAdminStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	public CompetitionPlayerStatusDTO(Long id, CompetitionPlayerStatusType competitionPlayerStatusDescription,
			Timestamp creationTime, Timestamp validFrom, Timestamp validTo) {
		super(id, competitionPlayerStatusDescription.name());
		this.competitionPlayerStatusDescription = competitionPlayerStatusDescription;
		this.creationTime = creationTime;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public CompetitionPlayerStatusDTO(CompetitionPlayerStatus competitionPlayerStatus, Timestamp validFrom,
			Timestamp validTo) {
		super(competitionPlayerStatus.getId(), competitionPlayerStatus.getCompetitionPlayerStatusDescription().name());
		this.competitionPlayerStatusDescription = competitionPlayerStatus.getCompetitionPlayerStatusDescription();
		this.creationTime = competitionPlayerStatus.getCreationTime();
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public CompetitionPlayerStatusDTO(CompetitionPlayerStatus competitionPlayerStatus) {
		super(competitionPlayerStatus.getId(), competitionPlayerStatus.getCompetitionPlayerStatusDescription().name());
		this.competitionPlayerStatusDescription = competitionPlayerStatus.getCompetitionPlayerStatusDescription();
		this.creationTime = competitionPlayerStatus.getCreationTime();
		this.validFrom = null;
		this.validTo = null;
	}

	public CompetitionPlayerStatusDTO(CompetitionPlayerStatusHistory competitionPlayerStatusHistory) {
		super(competitionPlayerStatusHistory.getId(), competitionPlayerStatusHistory.getCompetitionPlayerStatus()
				.getCompetitionPlayerStatusDescription().name());
		this.competitionPlayerStatusDescription = competitionPlayerStatusHistory.getCompetitionPlayerStatus()
				.getCompetitionPlayerStatusDescription();
		this.creationTime = competitionPlayerStatusHistory.getCompetitionPlayerStatus().getCreationTime();
		this.validFrom = competitionPlayerStatusHistory.getValidFrom();
		this.validTo = competitionPlayerStatusHistory.getValidTo();
	}

	public CompetitionPlayerStatusType getCompetitionPlayerStatusDescription() {
		return competitionPlayerStatusDescription;
	}

	public void setCompetitionPlayerStatusDescription(CompetitionPlayerStatusType competitionPlayerStatusDescription) {
		this.competitionPlayerStatusDescription = competitionPlayerStatusDescription;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

}
