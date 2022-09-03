package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingStatusService {

	private final BillingStatusRepository billingStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param billingStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the billing status
	 */
	@Autowired
	public BillingStatusService(BillingStatusRepository billingStatusRepository) {
		this.billingStatusRepository = billingStatusRepository;
	}

	private final Logger logger = LoggerFactory.getLogger(BillingStatusService.class);

	/**
	 * Function to fetch the status (if the status is already stored in the database) or
	 * to store the new status to the database
	 * @param billingStatusType the status type which should be stored
	 * @return The status which was created or was already in the database
	 */
	public BillingStatus getOrCreateBillingStatus(@NotNull BillingStatusType billingStatusType) {

		// Try to fetch the status from the database
		Optional<BillingStatus> billingStatus = billingStatusRepository.findByStatus(billingStatusType.name());

		BillingStatus finalBillingStatus;

		// If the status not in the database and create a new one
		if (billingStatus.isEmpty()) {
			finalBillingStatus = new BillingStatus(billingStatusType);
			billingStatusRepository.save(finalBillingStatus);
		}
		// If the status is in the database than return this one
		else {
			finalBillingStatus = billingStatus.get();
		}
		return finalBillingStatus;
	}

}
