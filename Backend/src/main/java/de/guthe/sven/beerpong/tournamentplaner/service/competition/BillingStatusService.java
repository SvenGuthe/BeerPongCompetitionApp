package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingStatusService {

	BillingStatusRepository billingStatusRepository;

	@Autowired
	public BillingStatusService(BillingStatusRepository billingStatusRepository) {
		this.billingStatusRepository = billingStatusRepository;
	}

	public BillingStatus getOrCreateBillingStatus(@NotNull BillingStatusType billingStatusType) {
		Optional<BillingStatus> billingStatus = billingStatusRepository.findByStatus(billingStatusType.name());
		return billingStatus.orElseGet(() -> new BillingStatus(billingStatusType));
	}

}
