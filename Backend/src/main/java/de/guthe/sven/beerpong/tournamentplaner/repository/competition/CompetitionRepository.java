package de.guthe.sven.beerpong.tournamentplaner.repository.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

	@Query("select distinct c from Competition c " + "join CompetitionStatusHistory csh on c.id = csh.competition.id "
			+ "join CompetitionStatus cs on cs.id = csh.competitionStatus.id " + "where csh.validTo is null "
			+ "and cs.competitionStatusType in ?1")
	Page<Competition> findCompetitionsByType(Collection<CompetitionStatusType> competitionStatusTypes,
			PageRequest pageRequest);

	@Query("select distinct c from Competition c " + "join CompetitionAdmin ca on c.id = ca.competition.id "
			+ "join CompetitionAdminStatusHistory cash on ca.id = cash.competitionAdmin.id "
			+ "join CompetitionTeam ct on c.id = ct.competition.id "
			+ "join CompetitionPlayer cp on ct.id = cp.competitionTeam.id "
			+ "WHERE (cash.competitionAdminStatus.competitionAdminStatusDescription = ?1 " + "AND cash.validTo is null "
			+ "AND ca.user = ?2) " + "or (cp.user = ?2 "
			+ "AND cp.competitionPlayerStatus.competitionPlayerStatusDescription = ?3)")
	Page<Competition> findOwnCompetitions(CompetitionAdminStatusType competitionAdminStatusType, User user,
			CompetitionPlayerStatusType competitionPlayerStatusType, PageRequest pageRequest);

}
