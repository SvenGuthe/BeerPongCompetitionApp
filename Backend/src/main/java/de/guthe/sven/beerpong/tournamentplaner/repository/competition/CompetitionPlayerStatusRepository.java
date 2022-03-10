package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetitionPlayerStatusRepository extends JpaRepository<CompetitionPlayerStatus, Long> {

    @Query(value = "SELECT * FROM CompetitionPlayerStatus cps WHERE LOWER(cps.competitionplayerstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR cps.id LIKE CONCAT(?1, '%')",
            countQuery = "SELECT count(*) FROM CompetitionPlayerStatus cps WHERE LOWER(cps.competitionplayerstatusdescription) LIKE CONCAT(LOWER(?1), '%') OR cps.id LIKE CONCAT(?1, '%')",
            nativeQuery = true)
    Page<CompetitionPlayerStatus> findAll(String search, PageRequest pageRequest);

    @Query("select cps from CompetitionPlayerStatus cps")
    Page<CompetitionPlayerStatus> findAll(PageRequest pageRequest);

}
