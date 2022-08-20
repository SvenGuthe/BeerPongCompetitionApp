package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamStatusService {

	TeamStatusRepository teamStatusRepository;

	public TeamStatusService(TeamStatusRepository teamStatusRepository) {
		this.teamStatusRepository = teamStatusRepository;
	}

	public TeamStatus getOrCreateTeamStatus(@NotNull TeamStatusType teamStatusType) {
		Optional<TeamStatus> teamStatus = teamStatusRepository.findByStatus(teamStatusType.name());
		TeamStatus finalTeamStatus;
		if (teamStatus.isEmpty()) {
			finalTeamStatus = new TeamStatus(teamStatusType);
			teamStatusRepository.save(finalTeamStatus);
		}
		else {
			finalTeamStatus = teamStatus.get();
		}

		return finalTeamStatus;
	}

}
