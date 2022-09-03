package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationStatusService {

	private final RegistrationStatusRepository registrationStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param registrationStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the registration status
	 */
	@Autowired
	public RegistrationStatusService(RegistrationStatusRepository registrationStatusRepository) {
		this.registrationStatusRepository = registrationStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(RegistrationStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param registrationStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public RegistrationStatus getOrCreateRegistrationStatus(@NotNull RegistrationStatusType registrationStatusType) {

		// Try to fetch the status from the database
		Optional<RegistrationStatus> registrationStatus = registrationStatusRepository
				.findByStatus(registrationStatusType.name());

		RegistrationStatus finalRegistrationStatus;

		// If the status not in the database and create a new one
		if (registrationStatus.isEmpty()) {
			finalRegistrationStatus = new RegistrationStatus(registrationStatusType);
			registrationStatusRepository.save(finalRegistrationStatus);
		}
		// If the status is in the database than return this one
		else {
			finalRegistrationStatus = registrationStatus.get();
		}

		return finalRegistrationStatus;
	}

}
