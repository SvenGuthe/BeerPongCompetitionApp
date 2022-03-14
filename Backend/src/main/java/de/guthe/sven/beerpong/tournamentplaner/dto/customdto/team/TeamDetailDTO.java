package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.TeamUserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;

import java.util.Collection;

public class TeamDetailDTO {

    TeamDTO team;

    Collection<TeamUserDTO> users;

    Collection<CompetitionDTO> competitions;

    public TeamDetailDTO(TeamDTO team, Collection<TeamUserDTO> users, Collection<CompetitionDTO> competitions) {
        this.team = team;
        this.users = users;
        this.competitions = competitions;
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

}
