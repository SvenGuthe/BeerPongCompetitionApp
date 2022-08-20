package de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionadmin;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionAdminStatusHistoryRepository extends JpaRepository<CompetitionAdminStatusHistory, Long> {

}
