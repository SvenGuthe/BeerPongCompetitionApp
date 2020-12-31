package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ACLSid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLSidRepository extends JpaRepository<ACLSid, Long> {

}