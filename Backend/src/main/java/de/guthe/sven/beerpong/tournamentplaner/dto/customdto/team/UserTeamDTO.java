package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamComposition;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class UserTeamDTO extends ID {

	@NotNull(message = "team in UserTeamDTO have to be set.")
	private TeamDTO team;

	@NotNull(message = "isAdmin in UserTeamDTO have to be set.")
	private Boolean isAdmin;

	@NotNull(message = "creationTime in UserTeamDTO have to be set.")
	private Timestamp creationTime;

	public UserTeamDTO(Long id, TeamDTO team, Boolean isAdmin, Timestamp creationTime) {
		super(id);
		this.team = team;
		this.isAdmin = isAdmin;
		this.creationTime = creationTime;
	}

	public UserTeamDTO(TeamComposition teamComposition) {
		super(teamComposition.getTeam().getId());
		this.team = new TeamDTO(teamComposition.getTeam());
		this.isAdmin = teamComposition.getAdmin();
		this.creationTime = teamComposition.getCreationTime();
	}

	public TeamDTO getTeam() {
		return team;
	}

	public void setTeam(TeamDTO team) {
		this.team = team;
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

}
