package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CompetitionPlayerDTO extends ID {

	@NotNull(message = "user in CompetitionPlayerDTO have to be set.")
	private UserDTO user;

	@NotNull(message = "competitionPlayerStatus in CompetitionPlayerDTO have to be set.")
	private CompetitionPlayerStatusDTO competitionPlayerStatus;

	@NotNull(message = "creationTime in CompetitionPlayerDTO have to be set.")
	private Timestamp creationTime;

	public CompetitionPlayerDTO(Long id, UserDTO user, CompetitionPlayerStatusDTO competitionPlayerStatus,
			Timestamp creationTime) {
		super(id);
		this.user = user;
		this.competitionPlayerStatus = competitionPlayerStatus;
		this.creationTime = creationTime;
	}

	public CompetitionPlayerDTO(CompetitionPlayer competitionPlayer) {
		super(competitionPlayer.getId());
		this.user = new UserDTO(competitionPlayer.getUser());
		this.competitionPlayerStatus = new CompetitionPlayerStatusDTO(competitionPlayer.getCompetitionPlayerStatus());
		this.creationTime = competitionPlayer.getCreationTime();
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public CompetitionPlayerStatusDTO getCompetitionPlayerStatus() {
		return competitionPlayerStatus;
	}

	public void setCompetitionPlayerStatus(CompetitionPlayerStatusDTO competitionPlayerStatus) {
		this.competitionPlayerStatus = competitionPlayerStatus;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

}