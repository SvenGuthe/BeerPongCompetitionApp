package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLSid;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLSidRepository;
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
public class ACLSidController {

	private final ACLSidRepository aclSidRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclSidRepository jpa repository to handle all database queries directly in
	 * this controller regarding the acl sids
	 */
	@Autowired
	public ACLSidController(ACLSidRepository aclSidRepository) {
		this.aclSidRepository = aclSidRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(ACLObjectIdentityController.class);

	/**
	 * Route to store a new ACL SID TODO: This is a debugging route and should be removed
	 * later in production
	 * @param aclSid Because there have to be no Data Transfer Object, we are using the
	 * model-class of the ACL SID
	 * @return the created ACL SID
	 */
	@PostMapping("/aclsid")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLSid addACLSid(@RequestBody ACLSid aclSid) {
		logger.info("Trying to store: " + aclSid);
		return aclSidRepository.save(aclSid);
	}

	/**
	 * Route to fetch all ACL SIDs TODO: Change this (if we need the route) to a paginated
	 * result
	 * @return all ACL SIDs
	 */
	@GetMapping("/aclsid")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public List<ACLSid> getACLSids() {
		logger.info("Fetch all ACL SIDs");
		return aclSidRepository.findAll();
	}

	/**
	 * Route to get a single ACL SID by the ID TODO: This is a debugging route and should
	 * be removed later in production
	 * @param aclSidId the id of the ACL SID in the database
	 * @return the ACL SID
	 */
	@GetMapping("/aclsid/{aclSidId}")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public ACLSid getACLSid(@PathVariable Long aclSidId) {
		logger.info("Trying to find an ACL SID with id: " + aclSidId);

		Optional<ACLSid> aclSid = aclSidRepository.findById(aclSidId);

		if (aclSid.isEmpty()) {
			throw new RuntimeException("ACL Sid not present with given id " + aclSidId);
		}
		else {
			return aclSid.get();
		}
	}

	/**
	 * Route to update the ACL SID manually TODO: This is a debugging route and should be
	 * removed later in production
	 * @param aclSid the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/aclsid")
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLSid updateACLSid(@RequestBody @NotNull ACLSid aclSid) {
		logger.info(
				"Trying to update the ACL SID with id = " + aclSid.getId() + " and the following content: " + aclSid);
		return aclSidRepository.save(aclSid);
	}

}
