package de.guthe.sven.beerpong.tournamentplaner.repository.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ACLEntryRepository extends JpaRepository<ACLEntry, Long> {
    List<Integer> findMasksByACLObjectIdentityACLSid(Long aclObjectIdentityId, Long aclSidId);
}