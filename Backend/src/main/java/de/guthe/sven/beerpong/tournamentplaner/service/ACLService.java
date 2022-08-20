package de.guthe.sven.beerpong.tournamentplaner.service;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLClassRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLEntryRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLObjectIdentityRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLSidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ACLService {

	ACLClassRepository aclClassRepository;

	ACLSidRepository aclSidRepository;

	ACLObjectIdentityRepository aclObjectIdentityRepository;

	ACLEntryRepository aclEntryRepository;

	UserRepository userRepository;

	JdbcMutableAclService aclService;

	@Autowired
	public ACLService(ACLClassRepository aclClassRepository, ACLSidRepository aclSidRepository,
			ACLObjectIdentityRepository aclObjectIdentityRepository, ACLEntryRepository aclEntryRepository,
			UserRepository userRepository, JdbcMutableAclService aclService) {
		this.aclClassRepository = aclClassRepository;
		this.aclSidRepository = aclSidRepository;
		this.aclObjectIdentityRepository = aclObjectIdentityRepository;
		this.aclEntryRepository = aclEntryRepository;
		this.userRepository = userRepository;
		this.aclService = aclService;
	}

	public void setPrivileges(ACLObjectInterface aclObjectInterface, Map<Sid, List<Permission>> permissions) {

		ObjectIdentity oi = new ObjectIdentityImpl(aclObjectInterface.getACLClass(), aclObjectInterface.getId());

		// Create or update the relevant ACL
		MutableAcl acl = null;
		try {
			acl = (MutableAcl) aclService.readAclById(oi);
		}
		catch (NotFoundException nfe) {
			acl = aclService.createAcl(oi);
		}

		for (Map.Entry<Sid, List<Permission>> entry : permissions.entrySet()) {
			Sid sid = entry.getKey();
			for (Permission permission : entry.getValue()) {
				acl.insertAce(acl.getEntries().size(), permission, sid, true);
			}
		}

		aclService.updateAcl(acl);

	}

}
