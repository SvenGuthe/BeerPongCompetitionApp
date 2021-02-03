package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query("select t from Team t join TeamStatusHistory tsh on t.id = tsh.team.id join TeamStatus ts on ts.id = tsh.teamStatus.id where tsh.validTo is null and ts.teamStatusDescription = 'ACTIVE' and t.isPlayerTeam = false")
	Page<Team> findAllActiveTeams(PageRequest pageRequest);

}
