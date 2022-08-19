package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamCompositionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamCompositionStatusRepository extends JpaRepository<TeamCompositionStatus, Long> {

	List<TeamCompositionStatus> findByDescription(TeamCompositionStatus teamCompositionStatus);

	@Query(value = "SELECT * FROM TeamCompositionStatus tcs WHERE LOWER(tcs.teamcompositionstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR tcs.id LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM TeamCompositionStatus tcs WHERE LOWER(tcs.teamcompositionstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR tcs.id LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<TeamCompositionStatus> findAll(String search, PageRequest pageRequest);

	@Query("select tcs from TeamCompositionStatus tcs")
	Page<TeamCompositionStatus> findAll(PageRequest pageRequest);

	@Query(value = "SELECT * FROM TeamCompositionStatus tcs WHERE LOWER(tcs.teamcompositionstatusdescription) = LOWER(?1)",
			nativeQuery = true)
	Optional<TeamCompositionStatus> findByStatus(String teamCompositionStatusType);

}
