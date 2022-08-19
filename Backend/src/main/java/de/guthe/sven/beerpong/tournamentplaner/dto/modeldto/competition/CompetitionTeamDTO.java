package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompetitionTeamDTO extends ID {

	@NotNull(message = "competitionTeamName in CompetitionTeamDTO have to be set.")
	private String competitionTeamName;

	@NotNull(message = "creationTime in CompetitionTeamDTO have to be set.")
	private Timestamp creationTime;

	private TeamDTO team;

	@NotNull(message = "competitionPlayer in CompetitionTeamDTO have to be set.")
	private Collection<CompetitionPlayerDTO> competitionPlayer;

	@NotNull(message = "billingStatus in CompetitionTeamDTO have to be set.")
	private Collection<BillingStatusDTO> billingStatus;

	@NotNull(message = "registrationStatus in CompetitionTeamDTO have to be set.")
	private Collection<RegistrationStatusDTO> registrationStatus;

	public CompetitionTeamDTO(Long id, String competitionTeamName, Timestamp creationTime, TeamDTO team,
			Collection<CompetitionPlayerDTO> competitionPlayer, Collection<BillingStatusDTO> billingStatus,
			Collection<RegistrationStatusDTO> registrationStatus) {
		super(id);
		this.competitionTeamName = competitionTeamName;
		this.creationTime = creationTime;
		this.team = team;
		this.competitionPlayer = competitionPlayer;
		this.billingStatus = billingStatus;
		this.registrationStatus = registrationStatus;
	}

	public CompetitionTeamDTO(CompetitionTeam competitionTeam) {
		super(competitionTeam.getId());
		this.competitionTeamName = competitionTeam.getCompetitionTeamName();
		this.creationTime = competitionTeam.getCreationTime();
		this.team = new TeamDTO(competitionTeam.getTeam());
		this.competitionPlayer = competitionTeam.getCompetitionPlayers().stream().map(CompetitionPlayerDTO::new)
				.collect(Collectors.toList());
		this.billingStatus = competitionTeam.getBillingStatusHistories().stream().map(BillingStatusDTO::new)
				.collect(Collectors.toList());
		this.registrationStatus = competitionTeam.getRegistrationStatusHistories().stream()
				.map(RegistrationStatusDTO::new).collect(Collectors.toList());
	}

	public String getCompetitionTeamName() {
		return competitionTeamName;
	}

	public void setCompetitionTeamName(String competitionTeamName) {
		this.competitionTeamName = competitionTeamName;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Collection<CompetitionPlayerDTO> getCompetitionPlayer() {
		return competitionPlayer;
	}

	public void setCompetitionPlayer(Collection<CompetitionPlayerDTO> competitionPlayer) {
		this.competitionPlayer = competitionPlayer;
	}

	public Collection<BillingStatusDTO> getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(Collection<BillingStatusDTO> billingStatus) {
		this.billingStatus = billingStatus;
	}

	public Collection<RegistrationStatusDTO> getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(Collection<RegistrationStatusDTO> registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

}
