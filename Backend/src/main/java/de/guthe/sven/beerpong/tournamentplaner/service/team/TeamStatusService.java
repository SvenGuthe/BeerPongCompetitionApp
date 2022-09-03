package de.guthe.sven.beerpong.tournamentplaner.service.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.BillingStatusService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamStatusService {

	private final TeamStatusRepository teamStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param teamStatusRepository jpa repository to handle all database queries directly
	 * in this controller regarding the team status
	 */
	public TeamStatusService(TeamStatusRepository teamStatusRepository) {
		this.teamStatusRepository = teamStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(TeamStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param teamStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public TeamStatus getOrCreateTeamStatus(@NotNull TeamStatusType teamStatusType) {

		// Try to fetch the status from the database
		Optional<TeamStatus> teamStatus = teamStatusRepository.findByStatus(teamStatusType.name());

		TeamStatus finalTeamStatus;

		// If the status not in the database and create a new one
		if (teamStatus.isEmpty()) {
			finalTeamStatus = new TeamStatus(teamStatusType);
			teamStatusRepository.save(finalTeamStatus);
		}
		// If the status is in the database than return this one
		else {
			finalTeamStatus = teamStatus.get();
		}

		return finalTeamStatus;
	}

}
