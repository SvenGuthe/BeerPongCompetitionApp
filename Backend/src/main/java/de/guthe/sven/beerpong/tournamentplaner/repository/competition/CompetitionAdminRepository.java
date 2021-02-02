package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionAdminRepository extends JpaRepository<CompetitionAdmin, Long> {

}
