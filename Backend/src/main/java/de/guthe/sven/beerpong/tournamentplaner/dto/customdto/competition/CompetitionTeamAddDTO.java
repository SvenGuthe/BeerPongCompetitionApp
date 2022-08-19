package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;
import java.util.Optional;

// ID = Competition ID
public class CompetitionTeamAddDTO extends ID {

	@NotNull(message = "teamname in CompetitionTeamAddDTO have to be set.")
	private String teamname;

	@NotNull(message = "password in CompetitionTeamAddDTO have to be set.")
	private String password;

	private Long teamId;

	@NotNull(message = "playerIds in CompetitionTeamAddDTO have to be set.")
	private Long[] playerIds;

	public CompetitionTeamAddDTO(Long id, String teamname, String password, Long teamId, Long[] playerIds) {
		super(id);
		this.teamname = teamname;
		this.password = password;
		this.teamId = teamId;
		this.playerIds = playerIds;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long[] getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(Long[] playerIds) {
		this.playerIds = playerIds;
	}

}
