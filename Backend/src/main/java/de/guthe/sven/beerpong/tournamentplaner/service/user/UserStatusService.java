package de.guthe.sven.beerpong.tournamentplaner.service.user;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.user.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.BillingStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserStatusService {

	private final UserStatusRepository userStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param userStatusRepository jpa repository to handle all database queries directly
	 * in this controller regarding the user status
	 */
	public UserStatusService(UserStatusRepository userStatusRepository) {
		this.userStatusRepository = userStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(UserStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param userStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public UserStatus getOrCreateUserStatus(UserStatusType userStatusType) {

		// Try to fetch the status from the database
		Optional<UserStatus> userStatus = userStatusRepository.findByStatus(userStatusType.name());

		UserStatus finalUserStatus;

		// If the status not in the database and create a new one
		if (userStatus.isEmpty()) {
			finalUserStatus = new UserStatus(userStatusType);
			userStatusRepository.save(finalUserStatus);
		}
		// If the status is in the database than return this one
		else {
			finalUserStatus = userStatus.get();
		}

		return finalUserStatus;
	}

}
