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

	private final JdbcMutableAclService aclService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclService service to handle all the transformations / database queries
	 * regarding the acl
	 */
	@Autowired
	public ACLService(JdbcMutableAclService aclService) {
		this.aclService = aclService;
	}

	/**
	 * Set privileges to an object
	 * @param aclObjectInterface the object where someone needs permissions
	 * @param permissions a map of (user(Sid) and List of permissions) which should be
	 * added to the user
	 */
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

		// Set the permissions for the sid
		for (Map.Entry<Sid, List<Permission>> entry : permissions.entrySet()) {
			Sid sid = entry.getKey();
			for (Permission permission : entry.getValue()) {
				acl.insertAce(acl.getEntries().size(), permission, sid, true);
			}
		}

		// Update the ACL for the given object
		aclService.updateAcl(acl);

	}

}
