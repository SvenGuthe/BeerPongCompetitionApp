package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

public class TeamAndUserDTO {

	@NotNull(message = "team in TeamAndUserDTO have to be set.")
	TeamIDAndNameDTO team;

	@NotNull(message = "users in TeamAndUserDTO have to be set.")
	Collection<UserIDAndGamerTagDTO> users;

	public TeamAndUserDTO(TeamIDAndNameDTO team, Collection<UserIDAndGamerTagDTO> users) {
		this.team = team;
		this.users = users;
	}

	public TeamAndUserDTO(Team team) {
		this.team = new TeamIDAndNameDTO(team);
		this.users = team.getTeamCompositions().stream()
				.map(teamComposition -> new UserIDAndGamerTagDTO(teamComposition.getUser()))
				.collect(Collectors.toList());
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

	@Override
	public String toString() {
		return "TeamAndUserDTO{" + "team=" + team + ", users=" + users + '}';
	}

}
