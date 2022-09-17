package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectIdentity;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLObjectIdentityRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acl")
public class ACLObjectIdentityController {

	private final ACLObjectIdentityRepository aclObjectIdentityRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclObjectIdentityRepository jpa repository to handle all database queries
	 * directly in this controller regarding the acl object identities
	 */
	@Autowired
	public ACLObjectIdentityController(ACLObjectIdentityRepository aclObjectIdentityRepository) {
		this.aclObjectIdentityRepository = aclObjectIdentityRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(ACLObjectIdentityController.class);

	/**
	 * Route to store a new ACL Object Identity TODO: This is a debugging route and should
	 * be removed later in production
	 * @param aclObjectIdentity Because there have to be no Data Transfer Object, we are
	 * using the model-class of the ACL Object Identity
	 * @return the created ACL Object Identity
	 */
	@PostMapping("/aclobjectidentity")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLObjectIdentity addACLObjectIdentity(@RequestBody ACLObjectIdentity aclObjectIdentity) {
		logger.info("Trying to store: " + aclObjectIdentity);
		return aclObjectIdentityRepository.save(aclObjectIdentity);
	}

	/**
	 * Route to fetch all ACL Object Identities TODO: Change this (if we need the route)
	 * to a paginated result
	 * @return all ACL Object Identities
	 */
	@GetMapping("/aclobjectidentity")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public List<ACLObjectIdentity> getACLObjectIdentities() {
		logger.info("Fetch all ACL Object Identities");
		return aclObjectIdentityRepository.findAll();
	}

	/**
	 * Route to get a single ACL Object Identity by the ID TODO: This is a debugging route
	 * and should be removed later in production
	 * @param aclObjectIdentityId the id of the ACL Object Identity in the database
	 * @return the ACL Object Identity
	 */
	@GetMapping("/aclobjectidentity/{aclObjectIdentityId}")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public ACLObjectIdentity getACLObjectIdentity(@PathVariable Long aclObjectIdentityId) {
		logger.info("Trying to find an ACL Object Identity with id: " + aclObjectIdentityId);

		Optional<ACLObjectIdentity> aclObjectIdentity = aclObjectIdentityRepository.findById(aclObjectIdentityId);

		if (aclObjectIdentity.isEmpty()) {
			throw new RuntimeException("ACL Object Identity not present with given id " + aclObjectIdentityId);
		}
		else {
			return aclObjectIdentity.get();
		}

	}

	/**
	 * Route to update the ACL Object Identity manually TODO: This is a debugging route
	 * and should be removed later in production
	 * @param aclObjectIdentity the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/aclobjectidentity")
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLObjectIdentity updateACLObjectIdentity(@RequestBody @NotNull ACLObjectIdentity aclObjectIdentity) {
		logger.info("Trying to update the ACL Object Identity with id = " + aclObjectIdentity.getId()
				+ " and the following content: " + aclObjectIdentity);
		return aclObjectIdentityRepository.save(aclObjectIdentity);
	}

}
