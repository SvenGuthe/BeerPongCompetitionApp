package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionadmin.CompetitionAdminStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionAdminStatusService {

	private final CompetitionAdminStatusRepository competitionAdminStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionAdminStatusRepository jpa repository to handle all database
	 * queries directly in this controller regarding the competition admin status
	 */
	@Autowired
	public CompetitionAdminStatusService(CompetitionAdminStatusRepository competitionAdminStatusRepository) {
		this.competitionAdminStatusRepository = competitionAdminStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(BillingStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param competitionAdminStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public CompetitionAdminStatus getOrCreateCompetitionAdminStatus(
			@NotNull CompetitionAdminStatusType competitionAdminStatusType) {

		// Try to fetch the status from the database
		Optional<CompetitionAdminStatus> competitionAdminStatus = competitionAdminStatusRepository
				.findByStatus(competitionAdminStatusType.name());

		CompetitionAdminStatus finalCompetitionAdminStatus;

		// If the status not in the database and create a new one
		if (competitionAdminStatus.isEmpty()) {
			finalCompetitionAdminStatus = new CompetitionAdminStatus(competitionAdminStatusType);
			competitionAdminStatusRepository.save(finalCompetitionAdminStatus);
		}
		// If the status is in the database than return this one
		else {
			finalCompetitionAdminStatus = competitionAdminStatus.get();
		}
		return finalCompetitionAdminStatus;
	}

}
