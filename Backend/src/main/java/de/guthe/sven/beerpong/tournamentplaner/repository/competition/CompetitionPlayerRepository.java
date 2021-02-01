package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionPlayerRepository extends JpaRepository<CompetitionPlayer, Long> {

}
