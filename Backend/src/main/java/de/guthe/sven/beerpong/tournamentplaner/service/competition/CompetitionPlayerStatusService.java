package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionplayer.CompetitionPlayerStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionPlayerStatusService {

	private final CompetitionPlayerStatusRepository competitionPlayerStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionPlayerStatusRepository jpa repository to handle all database
	 * queries directly in this controller regarding the competition player status
	 */
	@Autowired
	public CompetitionPlayerStatusService(CompetitionPlayerStatusRepository competitionPlayerStatusRepository) {
		this.competitionPlayerStatusRepository = competitionPlayerStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(CompetitionPlayerStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param competitionPlayerStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public CompetitionPlayerStatus getOrCreateCompetitionPlayerStatus(
			@NotNull CompetitionPlayerStatusType competitionPlayerStatusType) {

		// Try to fetch the status from the database
		Optional<CompetitionPlayerStatus> competitionPlayerStatus = competitionPlayerStatusRepository
				.findByStatus(competitionPlayerStatusType.name());

		CompetitionPlayerStatus finalCompetitionPlayerStatus;

		// If the status not in the database and create a new one
		if (competitionPlayerStatus.isEmpty()) {
			finalCompetitionPlayerStatus = new CompetitionPlayerStatus(competitionPlayerStatusType);
			competitionPlayerStatusRepository.save(finalCompetitionPlayerStatus);
		}
		// If the status is in the database than return this one
		else {
			finalCompetitionPlayerStatus = competitionPlayerStatus.get();
		}

		return finalCompetitionPlayerStatus;
	}

}
