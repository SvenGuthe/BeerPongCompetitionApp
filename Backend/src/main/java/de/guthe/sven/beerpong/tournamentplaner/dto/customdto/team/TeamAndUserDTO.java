package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import java.util.Collection;
import java.util.stream.Collectors;

public class TeamAndUserDTO {

    TeamIDAndNameDTO team;

    Collection<UserIDAndGamerTagDTO> users;

    public TeamAndUserDTO(TeamIDAndNameDTO team, Collection<UserIDAndGamerTagDTO> users) {
        this.team = team;
        this.users = users;
    }

    public TeamAndUserDTO(Team team) {
        this.team = new TeamIDAndNameDTO(team);
        this.users = team.getTeamCompositions().stream().map(teamComposition -> {
            return new UserIDAndGamerTagDTO(teamComposition.getUser());
        }).collect(Collectors.toList());
    }

    public TeamIDAndNameDTO getTeam() {
        return team;
    }

    public void setTeam(TeamIDAndNameDTO team) {
        this.team = team;
    }

    public Collection<UserIDAndGamerTagDTO> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserIDAndGamerTagDTO> users) {
        this.users = users;
    }
}
