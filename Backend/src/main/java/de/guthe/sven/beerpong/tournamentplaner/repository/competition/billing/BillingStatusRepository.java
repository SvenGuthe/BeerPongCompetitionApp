package de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BillingStatusRepository extends JpaRepository<BillingStatus, Long> {

	@Query(value = "SELECT * FROM BillingStatus bs WHERE LOWER(bs.billingstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR bs.id LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM BillingStatus bs WHERE LOWER(bs.billingstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR bs.id LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<BillingStatus> findAll(String search, PageRequest pageRequest);

	@Query("select bs from BillingStatus bs")
	Page<BillingStatus> findAll(PageRequest pageRequest);

	@Query(value = "SELECT * FROM BillingStatus bs WHERE LOWER(bs.billingstatusdescription) = LOWER(?1)",
			nativeQuery = true)
	Optional<BillingStatus> findByStatus(String billingStatusType);

}
