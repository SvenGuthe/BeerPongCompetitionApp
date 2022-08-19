package de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionadmin;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompetitionAdminStatusRepository extends JpaRepository<CompetitionAdminStatus, Long> {

	@Query(value = "SELECT * FROM CompetitionAdminStatus cas WHERE LOWER(cas.competitionadminstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR cas.id LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM CompetitionAdminStatus cas WHERE LOWER(cas.competitionadminstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR cas.id LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<CompetitionAdminStatus> findAll(String search, PageRequest pageRequest);

	@Query("select cas from CompetitionAdminStatus cas")
	Page<CompetitionAdminStatus> findAll(PageRequest pageRequest);

	@Query(value = "SELECT * FROM CompetitionAdminStatus cas WHERE LOWER(cas.competitionadminstatusdescription) = LOWER(?1)",
			nativeQuery = true)
	List<CompetitionAdminStatus> findByStatus(String competitionAdminStatusType);

}
