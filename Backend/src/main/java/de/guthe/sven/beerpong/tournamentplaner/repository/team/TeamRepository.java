package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
