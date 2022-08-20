package de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionPlayerStatusHistoryRepository extends JpaRepository<CompetitionPlayerStatusHistory, Long> {

}
