package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.TeamUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;

import java.util.Collection;

public class TeamDetailDTO {

    TeamDTO team;

    Collection<TeamUserDTO> users;

    Collection<CompetitionDTO> competitions;

    Collection<UserDTO> possibleUsers;

    public TeamDetailDTO(TeamDTO team, Collection<TeamUserDTO> users, Collection<CompetitionDTO> competitions, Collection<UserDTO> possibleUsers) {
        this.team = team;
        this.users = users;
        this.competitions = competitions;
        this.possibleUsers = possibleUsers;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public Collection<TeamUserDTO> getUsers() {
        return users;
    }

    public void setUsers(Collection<TeamUserDTO> users) {
        this.users = users;
    }

    public Collection<CompetitionDTO> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Collection<CompetitionDTO> competitions) {
        this.competitions = competitions;
    }

    public Collection<UserDTO> getPossibleUsers() {
        return possibleUsers;
    }

    public void setPossibleUsers(Collection<UserDTO> possibleUsers) {
        this.possibleUsers = possibleUsers;
    }
}
