package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.IUserIDAndGamerTagDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	@Query(value = "SELECT * FROM User u WHERE LOWER(u.gamertag) LIKE CONCAT(LOWER(?1), '%') OR LOWER(u.email) LIKE CONCAT(LOWER(?1), '%') OR u.userid LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM User u WHERE LOWER(u.gamertag) LIKE CONCAT(LOWER(?1), '%') OR LOWER(u.email) LIKE CONCAT(LOWER(?1), '%') OR u.userid LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<User> findAll(String search, PageRequest pageRequest);

	@Query("select u from User u")
	Page<User> findAll(PageRequest pageRequest);

	@Query(value = "SELECT DISTINCT u.userid, u.gamertag FROM User u " +
			"LEFT JOIN CompetitionAdmin ca ON u.userid = ca.userid " +
			"LEFT JOIN CompetitionAdminStatusHistory cash ON ca.competitionadminid = cash.competitionadminid " +
			"LEFT JOIN CompetitionAdminStatus cas ON cash.competitionadminstatusid = cas.competitionadminstatusid " +
			"LEFT JOIN Competition c ON ca.competitionid = c.competitionid " +
			"WHERE NOT (c.competitionid = ?1 AND cash.validto IS NULL AND cas.competitionadminstatusdescription IS NOT NULL AND cas.competitionadminstatusdescription IN ('INVITED', 'PROMISED'));",
			nativeQuery = true)
	Collection<IUserIDAndGamerTagDTO> findAllPossibleAdmins(Long competitionId);

	@Query(value = "SELECT DISTINCT u.userid, u.gamertag FROM User u " +
			"LEFT JOIN CompetitionPlayer cp ON u.userid = cp.userid " +
			"LEFT JOIN CompetitionPlayerStatus cps ON cp.competitionplayerstatusid = cps.competitionplayerstatusid " +
			"LEFT JOIN CompetitionTeam ct ON cp.competitionteamid = ct.competitionteamid " +
			"LEFT JOIN Competition c ON ct.competitionid = c.competitionid " +
			"WHERE NOT (c.competitionid = ?1 AND cps.competitionplayerstatusdescription IS NOT NULL AND cps.competitionplayerstatusdescription IN ('INVITED', 'PROMISED'))",
			nativeQuery = true)
	Collection<IUserIDAndGamerTagDTO> findAllPossiblePlayers(Long competitionId);

}
