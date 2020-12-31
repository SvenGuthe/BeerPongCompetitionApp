package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ACLEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLEntryRepository extends JpaRepository<ACLEntry, Long> {

}