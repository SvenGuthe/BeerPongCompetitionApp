package de.guthe.sven.beerpong.tournamentplaner.repository.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ACLObjectIdentityRepository extends JpaRepository<ACLObjectIdentity, Long> {

}