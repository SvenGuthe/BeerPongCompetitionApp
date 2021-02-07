package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionStatusHistoryRepository extends JpaRepository<CompetitionStatusHistory, Long> {

}
