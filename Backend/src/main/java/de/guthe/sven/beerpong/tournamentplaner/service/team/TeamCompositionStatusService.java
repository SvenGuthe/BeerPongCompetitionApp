package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamCompositionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.BillingStatusService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamCompositionStatusService {

	private final TeamCompositionStatusRepository teamCompositionStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param teamCompositionStatusRepository jpa repository to handle all database
	 * queries directly in this controller regarding the team composition status
	 */
	@Autowired
	public TeamCompositionStatusService(TeamCompositionStatusRepository teamCompositionStatusRepository) {
		this.teamCompositionStatusRepository = teamCompositionStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(TeamCompositionStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param teamCompositionStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public TeamCompositionStatus getOrCreateTeamCompositionStatus(
			@NotNull TeamCompositionStatusType teamCompositionStatusType) {

		// Try to fetch the status from the database
		Optional<TeamCompositionStatus> teamCompositionStatus = teamCompositionStatusRepository
				.findByStatus(teamCompositionStatusType.name());

		TeamCompositionStatus finalTeamCompositionStatus;

		// If the status not in the database and create a new one
		if (teamCompositionStatus.isEmpty()) {
			finalTeamCompositionStatus = new TeamCompositionStatus(teamCompositionStatusType);
			teamCompositionStatusRepository.save(finalTeamCompositionStatus);
		}
		// If the status is in the database than return this one
		else {
			finalTeamCompositionStatus = teamCompositionStatus.get();
		}

		return finalTeamCompositionStatus;
	}

}
