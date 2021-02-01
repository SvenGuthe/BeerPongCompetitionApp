package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionTeamRepository extends JpaRepository<CompetitionTeam, Long> {

}
