package de.guthe.sven.beerpong.tournamentplaner.repository.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.ACLObjectIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLObjectIdentityRepository extends JpaRepository<ACLObjectIdentity, Long> {

}