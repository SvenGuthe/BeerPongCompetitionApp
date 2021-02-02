package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStatusRepository extends JpaRepository<TeamStatus, Long> {

}
