package de.guthe.sven.beerpong.tournamentplaner.repository.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamStatusRepository extends JpaRepository<TeamStatus, Long> {

	List<TeamStatus> findByDescription(TeamStatusType teamstatustype);

	@Query(value = "SELECT * FROM TeamStatus ts WHERE LOWER(ts.teamstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR ts.id LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM TeamStatus ts WHERE LOWER(ts.teamstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR ts.id LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<TeamStatus> findAll(String search, PageRequest pageRequest);

	@Query("select p from TeamStatus p")
	Page<TeamStatus> findAll(PageRequest pageRequest);

	@Query(value = "SELECT * FROM TeamStatus ts WHERE LOWER(ts.teamstatusdescription) = LOWER(?1)", nativeQuery = true)
	Optional<TeamStatus> findByStatus(String teamStatusType);

}
