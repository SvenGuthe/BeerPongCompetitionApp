package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamcomposition.TeamCompositionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamComposition;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class TeamUserDTO extends ID {

	@NotNull(message = "user in TeamUserDTO have to be set.")
	private UserDTO user;

	@NotNull(message = "isAdmin in TeamUserDTO have to be set.")
	private Boolean isAdmin;

	@NotNull(message = "creationTime in TeamUserDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "teamComposition in TeamUserDTO have to be set.")
	private List<TeamCompositionStatusDTO> teamCompositionStatus;

	public TeamUserDTO(Long id, UserDTO user, Boolean isAdmin, Timestamp creationTime,
			List<TeamCompositionStatusDTO> teamCompositionStatus) {
		super(id);
		this.user = user;
		this.isAdmin = isAdmin;
		this.creationTime = creationTime;
		this.teamCompositionStatus = teamCompositionStatus;
	}

	public TeamUserDTO(TeamComposition teamComposition) {
		super(teamComposition.getId());
		this.user = new UserDTO(teamComposition.getUser());
		this.isAdmin = teamComposition.getAdmin();
		this.creationTime = teamComposition.getCreationTime();
		this.teamCompositionStatus = teamComposition.getTeamCompositionStatusHistories().stream()
				.map(TeamCompositionStatusDTO::new).collect(Collectors.toList());
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

	public List<TeamCompositionStatusDTO> getTeamCompositionStatus() {
		return teamCompositionStatus;
	}

	public void setTeamCompositionStatus(List<TeamCompositionStatusDTO> teamCompositionStatus) {
		this.teamCompositionStatus = teamCompositionStatus;
	}

	@Override
	public String toString() {
		return "TeamUserDTO{" + "user=" + user + ", isAdmin=" + isAdmin + ", creationTime=" + creationTime
				+ ", teamCompositionStatus=" + teamCompositionStatus + ", id=" + id + '}';
	}

}
