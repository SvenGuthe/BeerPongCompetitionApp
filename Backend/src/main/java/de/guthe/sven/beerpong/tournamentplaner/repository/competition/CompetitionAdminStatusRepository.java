package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionAdminStatusRepository extends JpaRepository<CompetitionAdminStatus, Long> {

}
