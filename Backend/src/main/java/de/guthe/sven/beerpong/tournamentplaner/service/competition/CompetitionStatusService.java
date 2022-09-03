package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionStatusService {

	private final CompetitionStatusRepository competitionStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition status
	 */
	@Autowired
	public CompetitionStatusService(CompetitionStatusRepository competitionStatusRepository) {
		this.competitionStatusRepository = competitionStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(CompetitionStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param competitionStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public CompetitionStatus getOrCreateCompetitionStatus(@NotNull CompetitionStatusType competitionStatusType) {

		// Try to fetch the status from the database
		Optional<CompetitionStatus> competitionStatus = competitionStatusRepository
				.findByStatus(competitionStatusType.name());

		CompetitionStatus finalCompetitionStatus;

		// If the status not in the database and create a new one
		if (competitionStatus.isEmpty()) {
			finalCompetitionStatus = new CompetitionStatus(competitionStatusType);
			competitionStatusRepository.save(finalCompetitionStatus);
		}
		// If the status is in the database than return this one
		else {
			finalCompetitionStatus = competitionStatus.get();
		}

		return finalCompetitionStatus;
	}

}
