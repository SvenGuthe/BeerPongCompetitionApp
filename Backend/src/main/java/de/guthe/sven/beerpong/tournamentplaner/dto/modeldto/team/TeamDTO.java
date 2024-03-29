package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teaminvitationlink.TeamInvitationLinkDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamstatus.TeamStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class TeamDTO extends ID {

	@NotNull(message = "teamName in TeamDTO have to be set.")
	private String teamName;

	@NotNull(message = "isPlayerTeam in TeamDTO have to be set.")
	private boolean isPlayerTeam;

	@NotNull(message = "creationTime in TeamDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "teamInvitationLinks in TeamDTO have to be set.")
	private Collection<TeamInvitationLinkDTO> teamInvitationLinks;

	@NotNull(message = "teamStatus in TeamDTO have to be set.")
	private Collection<TeamStatusDTO> teamStatus;

	// Won't be connected
	// private Collection<TeamCompositionDTO> teamCompositions;

	// Not included
	// private Collection<CompetitionTeamDTO> competitionTeams;

	public TeamDTO(Long id, String teamName, boolean isPlayerTeam, Timestamp creationTime,
			Collection<TeamInvitationLinkDTO> teamInvitationLinks, Collection<TeamStatusDTO> teamStatus) {
		super(id);
		this.teamName = teamName;
		this.isPlayerTeam = isPlayerTeam;
		this.creationTime = creationTime;
		this.teamInvitationLinks = teamInvitationLinks;
		this.teamStatus = teamStatus;
	}

	public TeamDTO(Team team) {
		super(team.getId());
		this.teamName = team.getTeamName();
		this.isPlayerTeam = team.isPlayerTeam();
		this.creationTime = team.getCreationTime();
		this.teamInvitationLinks = team.getTeamInvitationLinkHistories().stream().map(TeamInvitationLinkDTO::new)
				.collect(Collectors.toList());
		this.teamStatus = team.getTeamStatusHistories().stream().map(TeamStatusDTO::new).collect(Collectors.toList());
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public boolean isPlayerTeam() {
		return isPlayerTeam;
	}

	public void setPlayerTeam(boolean playerTeam) {
		isPlayerTeam = playerTeam;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Collection<TeamInvitationLinkDTO> getTeamInvitationLinks() {
		return teamInvitationLinks;
	}

	public void setTeamInvitationLinks(Collection<TeamInvitationLinkDTO> teamInvitationLinks) {
		this.teamInvitationLinks = teamInvitationLinks;
	}

	public Collection<TeamStatusDTO> getTeamStatus() {
		return teamStatus;
	}

	public void setTeamStatus(Collection<TeamStatusDTO> teamStatus) {
		this.teamStatus = teamStatus;
	}

	@Override
	public String toString() {
		return "TeamDTO{" + "teamName='" + teamName + '\'' + ", isPlayerTeam=" + isPlayerTeam + ", creationTime="
				+ creationTime + ", teamInvitationLinks=" + teamInvitationLinks + ", teamStatus=" + teamStatus + ", id="
				+ id + '}';
	}

}
