package de.guthe.sven.beerpong.tournamentplaner.repository.user;

import de.guthe.sven.beerpong.tournamentplaner.model.user.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(String name);

	@Query(value = "SELECT * FROM Role r WHERE LOWER(r.name) LIKE CONCAT(LOWER(?1), '%') OR r.id LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM Role r WHERE LOWER(r.name) LIKE CONCAT(LOWER(?1), '%') OR r.id LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<Role> findAll(String search, PageRequest pageRequest);

	@Query("select r from Role r")
	Page<Role> findAll(PageRequest pageRequest);

}
