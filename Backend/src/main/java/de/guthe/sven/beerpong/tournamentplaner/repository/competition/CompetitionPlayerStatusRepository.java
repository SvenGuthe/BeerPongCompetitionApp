package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionPlayerStatusRepository extends JpaRepository<CompetitionPlayerStatus, Long> {

}
