package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamCompositionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamCompositionStatusService {

	TeamCompositionStatusRepository teamCompositionStatusRepository;

	@Autowired
	public TeamCompositionStatusService(TeamCompositionStatusRepository teamCompositionStatusRepository) {
		this.teamCompositionStatusRepository = teamCompositionStatusRepository;
	}

	public TeamCompositionStatus getOrCreateTeamCompositionStatus(
			@NotNull TeamCompositionStatusType teamCompositionStatusType) {
		Optional<TeamCompositionStatus> teamCompositionStatus = teamCompositionStatusRepository
				.findByStatus(teamCompositionStatusType.name());
		TeamCompositionStatus finalTeamCompositionStatus;
		if (teamCompositionStatus.isEmpty()) {
			finalTeamCompositionStatus = new TeamCompositionStatus(teamCompositionStatusType);
			teamCompositionStatusRepository.save(finalTeamCompositionStatus);
		}
		else {
			finalTeamCompositionStatus = teamCompositionStatus.get();
		}

		return finalTeamCompositionStatus;
	}

}
