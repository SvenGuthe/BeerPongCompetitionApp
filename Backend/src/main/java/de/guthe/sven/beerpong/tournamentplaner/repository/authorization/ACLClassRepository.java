package de.guthe.sven.beerpong.tournamentplaner.repository.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLClassRepository extends JpaRepository<ACLClass, Long> {

}
