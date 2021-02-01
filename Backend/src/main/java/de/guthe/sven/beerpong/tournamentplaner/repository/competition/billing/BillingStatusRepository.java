package de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingStatusRepository extends JpaRepository<BillingStatus, Long> {
}
