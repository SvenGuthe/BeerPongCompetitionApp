package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	@Query(value = "SELECT * FROM Competition c WHERE LOWER(c.competitionname) LIKE CONCAT(LOWER(?1), '%') OR c.competitionid LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM Competition c WHERE LOWER(c.competitionname) LIKE CONCAT(LOWER(?1), '%') OR c.competitionid LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<Competition> findAll(String search, PageRequest pageRequest);

	@Query("select c from Competition c")
	Page<Competition> findAll(PageRequest pageRequest);

}
