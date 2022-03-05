package de.guthe.sven.beerpong.tournamentplaner.dto.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatusHistory;

import java.util.stream.Collectors;

public class TeamMetaDataDTO {

    private Long id;

    private String teamName;

    private TeamStatusType currentTeamStatusType;

    public TeamMetaDataDTO() {
    }

    public TeamMetaDataDTO(Long id, String teamName, TeamStatusType currentTeamStatusType) {
        this.id = id;
        this.teamName = teamName;
        this.currentTeamStatusType = currentTeamStatusType;
    }

    public TeamMetaDataDTO(Team team) {
        this.id = team.getId();
        this.teamName = team.getTeamName();
        this.currentTeamStatusType = team.getTeamStatusHistories().stream().filter(
                teamStatusHistory -> teamStatusHistory.getValidTo() == null
        ).map(TeamStatusHistory::getTeamStatus).collect(Collectors.toList()).get(0).getTeamStatusDescription();
    }

    public Class<Team> getACLClass() {
        return Team.class;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public TeamStatusType getCurrentTeamStatusType() {
        return currentTeamStatusType;
    }

    public void setCurrentTeamStatusType(TeamStatusType currentTeamStatusType) {
        this.currentTeamStatusType = currentTeamStatusType;
    }
}
