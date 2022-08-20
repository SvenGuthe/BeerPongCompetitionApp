package de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionPlayerRepository extends JpaRepository<CompetitionPlayer, Long> {

}
