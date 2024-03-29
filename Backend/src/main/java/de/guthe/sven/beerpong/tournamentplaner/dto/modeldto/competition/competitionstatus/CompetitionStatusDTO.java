package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionstatus;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatusHistory;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CompetitionStatusDTO extends EnumDTO {

	@NotNull(message = "competitionStatusType in CompetitionStatusDTO have to be set.")
	private CompetitionStatusType competitionStatusType;

	@NotNull(message = "creationTime in CompetitionStatusDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "validFrom in CompetitionStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	public CompetitionStatusDTO(Long id, CompetitionStatusType competitionStatusType, Timestamp creationTime,
			Timestamp validFrom, Timestamp validTo) {
		super(id, competitionStatusType.name());
		this.competitionStatusType = competitionStatusType;
		this.creationTime = creationTime;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public CompetitionStatusDTO(CompetitionStatus competitionStatus, Timestamp validFrom, Timestamp validTo) {
		super(competitionStatus.getId(), competitionStatus.getCompetitionStatusType().name());
		this.competitionStatusType = competitionStatus.getCompetitionStatusType();
		this.creationTime = competitionStatus.getCreationTime();
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public CompetitionStatusDTO(CompetitionStatus competitionStatus) {
		super(competitionStatus.getId(), competitionStatus.getCompetitionStatusType().name());
		this.competitionStatusType = competitionStatus.getCompetitionStatusType();
		this.creationTime = competitionStatus.getCreationTime();
		this.validFrom = null;
		this.validTo = null;
	}

	public CompetitionStatusDTO(CompetitionStatusHistory competitionStatusHistory) {
		super(competitionStatusHistory.getId(),
				competitionStatusHistory.getCompetitionStatus().getCompetitionStatusType().name());
		this.competitionStatusType = competitionStatusHistory.getCompetitionStatus().getCompetitionStatusType();
		this.creationTime = competitionStatusHistory.getCompetitionStatus().getCreationTime();
		this.validFrom = competitionStatusHistory.getValidFrom();
		this.validTo = competitionStatusHistory.getValidTo();
	}

	public CompetitionStatusType getCompetitionStatusType() {
		return competitionStatusType;
	}

	public void setCompetitionStatusType(CompetitionStatusType competitionStatusType) {
		this.competitionStatusType = competitionStatusType;
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

	@Override
	public String toString() {
		return "CompetitionStatusDTO{" + "competitionStatusType=" + competitionStatusType + ", creationTime="
				+ creationTime + ", validFrom=" + validFrom + ", validTo=" + validTo + ", id=" + id + '}';
	}

}
