package de.guthe.sven.beerpong.tournamentplaner.repository.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ACLClassRepository extends JpaRepository<ACLClass, Long> {

	ACLClass findByClassName(String className);

	@Query(value = "SELECT * FROM acl_class as acl WHERE LOWER(acl.class) LIKE CONCAT(LOWER(?1), '%') OR acl.id LIKE CONCAT(?1, '%')",
			countQuery = "SELECT count(*) FROM acl_class as acl WHERE LOWER(acl.class) LIKE CONCAT(LOWER(?1), '%') OR acl.id LIKE CONCAT(?1, '%')",
			nativeQuery = true)
	Page<ACLClass> findAll(String search, PageRequest pageRequest);

	@Query("select acl from ACLClass acl")
	Page<ACLClass> findAll(PageRequest pageRequest);

}
