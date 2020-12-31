package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ACLClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLClassRepository extends JpaRepository<ACLClass, Long> {

}
