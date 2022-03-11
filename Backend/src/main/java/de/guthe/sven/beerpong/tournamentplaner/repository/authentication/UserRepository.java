package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	@Query(value = "SELECT * FROM User u WHERE LOWER(u.gamertag) LIKE CONCAT(LOWER(?1), '%') OR LOWER(u.email) LIKE CONCAT(LOWER(?1), '%') OR u.userid LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM User u WHERE LOWER(u.gamertag) LIKE CONCAT(LOWER(?1), '%') OR LOWER(u.email) LIKE CONCAT(LOWER(?1), '%') OR u.userid LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<User> findAll(String search, PageRequest pageRequest);

	@Query("select u from User u")
	Page<User> findAll(PageRequest pageRequest);

}
