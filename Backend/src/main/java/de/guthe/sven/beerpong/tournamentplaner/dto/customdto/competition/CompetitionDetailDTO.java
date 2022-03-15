package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamAndUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;

import java.util.Collection;

public class CompetitionDetailDTO {

    CompetitionDTO competition;

    Collection<UserIDAndGamerTagDTO> users;

    Collection<TeamAndUserDTO> teams;

    public CompetitionDetailDTO(CompetitionDTO competition, Collection<UserIDAndGamerTagDTO> users, Collection<TeamAndUserDTO> teams) {
        this.competition = competition;
        this.users = users;
        this.teams = teams;
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDTO competition) {
        this.competition = competition;
    }

    public Collection<UserIDAndGamerTagDTO> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserIDAndGamerTagDTO> users) {
        this.users = users;
    }

    public Collection<TeamAndUserDTO> getTeams() {
        return teams;
    }

    public void setTeams(Collection<TeamAndUserDTO> teams) {
        this.teams = teams;
    }

}
