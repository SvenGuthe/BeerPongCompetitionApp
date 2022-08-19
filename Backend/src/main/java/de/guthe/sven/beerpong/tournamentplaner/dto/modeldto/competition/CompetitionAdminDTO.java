package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdmin;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.stream.Collectors;

public class CompetitionAdminDTO extends ID {

	@NotNull(message = "user in CompetitionAdminDTO have to be set.")
	private UserDTO user;

	@NotNull(message = "creationTime in CompetitionAdminDTO have to be set.")
	private Timestamp creationTime;

	@NotNull(message = "competitionAdminStatus in CompetitionAdminDTO have to be set.")
	private Collection<CompetitionAdminStatusDTO> competitionAdminStatus;

	public CompetitionAdminDTO(Long id, UserDTO user, Timestamp creationTime,
			Collection<CompetitionAdminStatusDTO> competitionAdminStatus) {
		super(id);
		this.user = user;
		this.creationTime = creationTime;
		this.competitionAdminStatus = competitionAdminStatus;
	}

	public CompetitionAdminDTO(CompetitionAdmin competitionAdmin) {
		super(competitionAdmin.getId());
		this.user = new UserDTO(competitionAdmin.getUser());
		this.creationTime = competitionAdmin.getCreationTime();
		this.competitionAdminStatus = competitionAdmin.getCompetitionAdminStatusHistories().stream()
				.map(CompetitionAdminStatusDTO::new).collect(Collectors.toList());
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Collection<CompetitionAdminStatusDTO> getCompetitionAdminStatus() {
		return competitionAdminStatus;
	}

	public void setCompetitionAdminStatus(Collection<CompetitionAdminStatusDTO> competitionAdminStatus) {
		this.competitionAdminStatus = competitionAdminStatus;
	}

}
