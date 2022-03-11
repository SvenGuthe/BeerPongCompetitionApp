package de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillingStatusRepository extends JpaRepository<BillingStatus, Long> {

    @Query(value = "SELECT * FROM BillingStatus bs WHERE LOWER(bs.billingstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR bs.id LIKE CONCAT(?1, '%')",
            countQuery = "SELECT count(*) FROM BillingStatus bs WHERE LOWER(bs.billingstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR bs.id LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    Page<BillingStatus> findAll(String search, PageRequest pageRequest);

    @Query("select bs from BillingStatus bs")
    Page<BillingStatus> findAll(PageRequest pageRequest);

}
