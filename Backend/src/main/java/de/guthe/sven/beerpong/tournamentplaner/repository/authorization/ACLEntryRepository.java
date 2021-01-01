package de.guthe.sven.beerpong.tournamentplaner.repository.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLEntryRepository extends JpaRepository<ACLEntry, Long> {

}