package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetitionStatusRepository extends JpaRepository<CompetitionStatus, Long> {

    @Query(value = "SELECT * FROM CompetitionStatus cs WHERE LOWER(cs.competitionstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR cs.id LIKE CONCAT(?1, '%')",
            countQuery = "SELECT count(*) FROM CompetitionStatus cs WHERE LOWER(cs.competitionstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR cs.id LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    Page<CompetitionStatus> findAll(String search, PageRequest pageRequest);

    @Query("select cs from CompetitionStatus cs")
    Page<CompetitionStatus> findAll(PageRequest pageRequest);

}
