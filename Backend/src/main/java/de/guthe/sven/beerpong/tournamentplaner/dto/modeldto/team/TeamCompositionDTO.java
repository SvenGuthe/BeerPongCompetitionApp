package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamComposition;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class TeamCompositionDTO extends ID {

	@NotNull(message = "team in TeamCompositionDTO have to be set.")
	private TeamDTO team;

	@NotNull(message = "user in TeamCompositionDTO have to be set.")
	private UserDTO user;

	@NotNull(message = "isAdmin in TeamCompositionDTO have to be set.")
	private Boolean isAdmin;

	@NotNull(message = "creationTime in TeamCompositionDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "teamCompositionStatusDTOS in TeamCompositionDTO have to be set.")
	private Collection<TeamCompositionStatusDTO> teamCompositionStatus;

	public TeamCompositionDTO(Long id, TeamDTO team, UserDTO user, Boolean isAdmin, Timestamp creationTime, Collection<TeamCompositionStatusDTO> teamCompositionStatus) {
		super(id);
		this.team = team;
		this.user = user;
		this.isAdmin = isAdmin;
		this.creationTime = creationTime;
		this.teamCompositionStatus = teamCompositionStatus;
	}

	public TeamCompositionDTO(TeamComposition teamComposition) {
		super(teamComposition.getId());
		this.team = new TeamDTO(teamComposition.getTeam());
		this.user = new UserDTO(teamComposition.getUser());
		this.isAdmin = teamComposition.getAdmin();
		this.creationTime = teamComposition.getCreationTime();
		this.teamCompositionStatus = teamComposition.getTeamCompositionStatusHistories().stream().map(TeamCompositionStatusDTO::new).collect(Collectors.toList());
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Collection<TeamCompositionStatusDTO> getTeamCompositionStatus() {
		return teamCompositionStatus;
	}

	public void setTeamCompositionStatus(Collection<TeamCompositionStatusDTO> teamCompositionStatus) {
		this.teamCompositionStatus = teamCompositionStatus;
	}
}
