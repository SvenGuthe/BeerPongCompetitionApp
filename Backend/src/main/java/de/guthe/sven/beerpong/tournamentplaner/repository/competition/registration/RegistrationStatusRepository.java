package de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationStatusRepository extends JpaRepository<RegistrationStatus, Long> {

}
