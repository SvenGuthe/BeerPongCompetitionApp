package de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingStatusHistoryRepository extends JpaRepository<BillingStatusHistory, Long> {
}
