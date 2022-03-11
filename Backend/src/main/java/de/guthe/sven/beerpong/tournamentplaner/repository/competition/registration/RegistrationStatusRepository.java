package de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegistrationStatusRepository extends JpaRepository<RegistrationStatus, Long> {

    @Query(value = "SELECT * FROM RegistrationStatus rs WHERE LOWER(rs.registrationstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR rs.id LIKE CONCAT(?1, '%')",
            countQuery = "SELECT count(*) FROM RegistrationStatus rs WHERE LOWER(rs.registrationstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR rs.id LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    Page<RegistrationStatus> findAll(String search, PageRequest pageRequest);

    @Query("select rs from RegistrationStatus rs")
    Page<RegistrationStatus> findAll(PageRequest pageRequest);

}
