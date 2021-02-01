package de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationStatusHistoryRepository extends JpaRepository<RegistrationStatusHistory, Long> {
}
