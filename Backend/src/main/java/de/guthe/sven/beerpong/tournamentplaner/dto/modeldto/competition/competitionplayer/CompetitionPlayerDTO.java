package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompetitionPlayerDTO extends ID {

	@NotNull(message = "user in CompetitionPlayerDTO have to be set.")
	private UserDTO user;

	@NotNull(message = "competitionPlayerStatus in CompetitionPlayerDTO have to be set.")
	private Collection<CompetitionPlayerStatusDTO> competitionPlayerStatus;

	@NotNull(message = "creationTime in CompetitionPlayerDTO have to be set.")
	private Timestamp creationTime;

	public CompetitionPlayerDTO(Long id, UserDTO user, Collection<CompetitionPlayerStatusDTO> competitionPlayerStatus,
			Timestamp creationTime) {
		super(id);
		this.user = user;
		this.competitionPlayerStatus = competitionPlayerStatus;
		this.creationTime = creationTime;
	}

	public CompetitionPlayerDTO(CompetitionPlayer competitionPlayer) {
		super(competitionPlayer.getId());
		this.user = new UserDTO(competitionPlayer.getUser());
		this.competitionPlayerStatus = competitionPlayer.getCompetitionPlayerStatusHistories().stream()
				.map(CompetitionPlayerStatusDTO::new).collect(Collectors.toList());
		this.creationTime = competitionPlayer.getCreationTime();
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Collection<CompetitionPlayerStatusDTO> getCompetitionPlayerStatus() {
		return competitionPlayerStatus;
	}

	public void setCompetitionPlayerStatus(Collection<CompetitionPlayerStatusDTO> competitionPlayerStatus) {
		this.competitionPlayerStatus = competitionPlayerStatus;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "CompetitionPlayerDTO{" + "user=" + user + ", competitionPlayerStatus=" + competitionPlayerStatus
				+ ", creationTime=" + creationTime + ", id=" + id + '}';
	}

}
