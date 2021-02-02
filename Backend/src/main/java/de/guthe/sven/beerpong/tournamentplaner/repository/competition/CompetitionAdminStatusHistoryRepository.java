package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionAdminStatusHistoryRepository extends JpaRepository<CompetitionAdminStatusHistory, Long> {

}
