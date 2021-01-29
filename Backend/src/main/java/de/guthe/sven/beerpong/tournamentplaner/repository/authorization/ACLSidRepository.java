package de.guthe.sven.beerpong.tournamentplaner.repository.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLSid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLSidRepository extends JpaRepository<ACLSid, Long> {

	ACLSid findByEmail(String email);

}