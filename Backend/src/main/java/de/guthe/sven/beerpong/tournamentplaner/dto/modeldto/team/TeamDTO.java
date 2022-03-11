package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class TeamDTO extends ID {

    private String teamName;

    private boolean isPlayerTeam;

    private Timestamp creationTime;

    private Collection<TeamInvitationLinkDTO> teamInvitationLinks;

    private Collection<TeamStatusDTO> teamStatus;

    // Won't be connected
    // private Collection<TeamCompositionDTO> teamCompositions;

    // Not included
    // private Collection<CompetitionTeamDTO> competitionTeams;

    public TeamDTO(Long id, String teamName, boolean isPlayerTeam, Timestamp creationTime, Collection<TeamInvitationLinkDTO> teamInvitationLinks, Collection<TeamStatusDTO> teamStatus) {
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
        this.teamInvitationLinks = team.getTeamInvitationLinkHistories().stream().map(TeamInvitationLinkDTO::new).collect(Collectors.toList());
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
}
