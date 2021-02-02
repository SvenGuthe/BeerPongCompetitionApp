package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStatusHistoryRepository extends JpaRepository<TeamStatusHistory, Long> {

}
