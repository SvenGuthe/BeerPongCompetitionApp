package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamAndUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public class CompetitionDetailDTO {

    @NotNull(message = "competition in CompetitionDetailDTO have to be set.")
    CompetitionDTO competition;

    @NotNull(message = "possibleAdminUsers in CompetitionDetailDTO have to be set.")
    Collection<UserIDAndGamerTagDTO> possibleAdminUsers;

    @NotNull(message = "possiblePlayers in CompetitionDetailDTO have to be set.")
    Collection<UserIDAndGamerTagDTO> possiblePlayers;

    @NotNull(message = "teams in CompetitionDetailDTO have to be set.")
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
