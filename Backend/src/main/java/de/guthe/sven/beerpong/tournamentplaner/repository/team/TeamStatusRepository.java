package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamStatusRepository extends JpaRepository<TeamStatus, Long> {

    List<TeamStatus> findByDescription(TeamStatusType teamstatustype);

}
