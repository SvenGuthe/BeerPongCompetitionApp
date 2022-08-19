package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatusHistory;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CompetitionAdminStatusDTO extends EnumDTO {

	@NotNull(message = "competitionAdminStatusDescription in CompetitionAdminStatusDTO have to be set.")
	private CompetitionAdminStatusType competitionAdminStatusDescription;

	@NotNull(message = "creationTime in CompetitionAdminStatusDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "validFrom in CompetitionAdminStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	public CompetitionAdminStatusDTO(Long id, CompetitionAdminStatusType competitionAdminStatusDescription,
			Timestamp creationTime, Timestamp validFrom, Timestamp validTo) {
		super(id, competitionAdminStatusDescription.name());
		this.competitionAdminStatusDescription = competitionAdminStatusDescription;
		this.creationTime = creationTime;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public CompetitionAdminStatusDTO(CompetitionAdminStatus competitionAdminStatus, Timestamp validFrom,
			Timestamp validTo) {
		super(competitionAdminStatus.getId(), competitionAdminStatus.getCompetitionAdminStatusDescription().name());
		this.competitionAdminStatusDescription = competitionAdminStatus.getCompetitionAdminStatusDescription();
		this.creationTime = competitionAdminStatus.getCreationTime();
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public CompetitionAdminStatusDTO(CompetitionAdminStatus competitionAdminStatus) {
		super(competitionAdminStatus.getId(), competitionAdminStatus.getCompetitionAdminStatusDescription().name());
		this.competitionAdminStatusDescription = competitionAdminStatus.getCompetitionAdminStatusDescription();
		this.creationTime = competitionAdminStatus.getCreationTime();
		this.validFrom = null;
		this.validTo = null;
	}

	public CompetitionAdminStatusDTO(CompetitionAdminStatusHistory competitionAdminStatusHistory) {
		super(competitionAdminStatusHistory.getId(), competitionAdminStatusHistory.getCompetitionAdminStatus()
				.getCompetitionAdminStatusDescription().name());
		this.competitionAdminStatusDescription = competitionAdminStatusHistory.getCompetitionAdminStatus()
				.getCompetitionAdminStatusDescription();
		this.creationTime = competitionAdminStatusHistory.getCompetitionAdminStatus().getCreationTime();
		this.validFrom = competitionAdminStatusHistory.getValidFrom();
		this.validTo = competitionAdminStatusHistory.getValidTo();
	}

	public CompetitionAdminStatusType getCompetitionAdminStatusDescription() {
		return competitionAdminStatusDescription;
	}

	public void setCompetitionAdminStatusDescription(CompetitionAdminStatusType competitionAdminStatusDescription) {
		this.competitionAdminStatusDescription = competitionAdminStatusDescription;
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
