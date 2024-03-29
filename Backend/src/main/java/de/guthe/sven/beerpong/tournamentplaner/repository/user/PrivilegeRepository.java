package de.guthe.sven.beerpong.tournamentplaner.repository.user;

import de.guthe.sven.beerpong.tournamentplaner.model.user.Privilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	Privilege findByName(String name);

	@Query(value = "SELECT * FROM Privilege p WHERE LOWER(p.privilege) LIKE CONCAT(LOWER(?1), '%') OR p.privilegeid LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM Privilege p WHERE LOWER(p.privilege) LIKE CONCAT(LOWER(?1), '%') OR p.privilegeid LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<Privilege> findAll(String search, PageRequest pageRequest);

	@Query("select p from Privilege p")
	Page<Privilege> findAll(PageRequest pageRequest);

}
