package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamAndUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;

import java.util.Collection;

public class CompetitionDetailDTO {

    CompetitionDTO competition;

    Collection<UserIDAndGamerTagDTO> possibleAdminUsers;

    Collection<UserIDAndGamerTagDTO> possiblePlayers;

    Collection<TeamAndUserDTO> teams;

    public CompetitionDetailDTO(CompetitionDTO competition, Collection<UserIDAndGamerTagDTO> possibleAdminUsers, Collection<UserIDAndGamerTagDTO> possiblePlayers, Collection<TeamAndUserDTO> teams) {
        this.competition = competition;
        this.possibleAdminUsers = possibleAdminUsers;
        this.possiblePlayers = possiblePlayers;
        this.teams = teams;
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDTO competition) {
        this.competition = competition;
    }

    public Collection<UserIDAndGamerTagDTO> getPossibleAdminUsers() {
        return possibleAdminUsers;
    }

    public void setPossibleAdminUsers(Collection<UserIDAndGamerTagDTO> possibleAdminUsers) {
        this.possibleAdminUsers = possibleAdminUsers;
    }

    public Collection<UserIDAndGamerTagDTO> getPossiblePlayers() {
        return possiblePlayers;
    }

    public void setPossiblePlayers(Collection<UserIDAndGamerTagDTO> possiblePlayers) {
        this.possiblePlayers = possiblePlayers;
    }

    public Collection<TeamAndUserDTO> getTeams() {
        return teams;
    }

    public void setTeams(Collection<TeamAndUserDTO> teams) {
        this.teams = teams;
    }

}
