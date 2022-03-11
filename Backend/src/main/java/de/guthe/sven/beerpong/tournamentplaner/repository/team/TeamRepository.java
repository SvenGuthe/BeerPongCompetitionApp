package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

	@Query(value = "SELECT * FROM Team t WHERE LOWER(t.teamname) LIKE CONCAT(LOWER(?1), '%') OR t.teamid LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM Team t WHERE LOWER(t.teamname) LIKE CONCAT(LOWER(?1), '%') OR t.teamid LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<Team> findAll(String search, PageRequest pageRequest);

	@Query("select t from Team t")
	Page<Team> findAll(PageRequest pageRequest);

}
