package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamstatus;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatusHistory;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class TeamStatusDTO extends EnumDTO {

	@NotNull(message = "teamStatusDescription in TeamStatusDTO have to be set.")
	private TeamStatusType teamStatusDescription;

	@NotNull(message = "creationTime in TeamStatusDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "validFrom in TeamStatusDTO have to be set.")
	private Timestamp validFrom;

	private Timestamp validTo;

	public TeamStatusDTO(Long id, TeamStatusType teamStatusDescription, Timestamp creationTime, Timestamp validFrom,
			Timestamp validTo) {
		super(id, teamStatusDescription.name());
		this.teamStatusDescription = teamStatusDescription;
		this.creationTime = creationTime;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public TeamStatusDTO(TeamStatus teamStatus, Timestamp validFrom, Timestamp validTo) {
		super(teamStatus.getId(), teamStatus.getTeamStatusDescription().name());
		this.teamStatusDescription = teamStatus.getTeamStatusDescription();
		this.creationTime = teamStatus.getCreationTime();
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public TeamStatusDTO(TeamStatus teamStatus) {
		super(teamStatus.getId(), teamStatus.getTeamStatusDescription().name());
		this.teamStatusDescription = teamStatus.getTeamStatusDescription();
		this.creationTime = teamStatus.getCreationTime();
		this.validFrom = null;
		this.validTo = null;
	}

	public TeamStatusDTO(TeamStatusHistory teamStatusHistory) {
		super(teamStatusHistory.getId(), teamStatusHistory.getTeamStatus().getTeamStatusDescription().name());
		this.teamStatusDescription = teamStatusHistory.getTeamStatus().getTeamStatusDescription();
		this.creationTime = teamStatusHistory.getTeamStatus().getCreationTime();
		this.validFrom = teamStatusHistory.getValidFrom();
		this.validTo = teamStatusHistory.getValidTo();
	}

	public TeamStatusType getTeamStatusDescription() {
		return teamStatusDescription;
	}

	public void setTeamStatusDescription(TeamStatusType teamStatusDescription) {
		this.teamStatusDescription = teamStatusDescription;
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
		return "TeamStatusDTO{" + "teamStatusDescription=" + teamStatusDescription + ", creationTime=" + creationTime
				+ ", validFrom=" + validFrom + ", validTo=" + validTo + ", id=" + id + '}';
	}

}
