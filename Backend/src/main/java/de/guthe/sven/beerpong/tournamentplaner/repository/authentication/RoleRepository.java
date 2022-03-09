package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

	@Query(value = "SELECT * FROM Role r WHERE LOWER(r.name) LIKE CONCAT(LOWER(?1), '%') OR r.roleid LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM Role r WHERE LOWER(r.name) LIKE CONCAT(LOWER(?1), '%') OR r.roleid LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<Role> findAll(String search, PageRequest pageRequest);

	@Query("select r from Role r")
	Page<Role> findAll(PageRequest pageRequest);

}
