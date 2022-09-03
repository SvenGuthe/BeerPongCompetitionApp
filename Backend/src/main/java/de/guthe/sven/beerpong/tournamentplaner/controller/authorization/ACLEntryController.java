package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLEntry;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLEntryRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLEntryController {

	private final ACLEntryRepository aclEntryRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclEntryRepository jpa repository to handle all database queries directly in
	 * this controller regarding the acl entries
	 */
	@Autowired
	public ACLEntryController(ACLEntryRepository aclEntryRepository) {
		this.aclEntryRepository = aclEntryRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(ACLEntryController.class);

	/**
	 * Route to store a new ACL Entry TODO: This is a debugging route and should be
	 * removed later in production
	 * @param aclEntry Because there have to be no Data Transfer Object, we are using the
	 * model-class of the ACL Entry
	 * @return the created ACL Entry
	 */
	@PostMapping("/aclentry")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLEntry addACLEntry(@RequestBody ACLEntry aclEntry) {
		logger.info("Trying to store: " + aclEntry);
		return aclEntryRepository.save(aclEntry);
	}

	/**
	 * Route to fetch all ACL Entries TODO: Change this (if we need the route) to a
	 * paginated result
	 * @return all ACL Entries
	 */
	@GetMapping("/aclentry")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public List<ACLEntry> getACLEntries() {
		logger.info("Fetch all ACL Entries");
		return aclEntryRepository.findAll();
	}

	/**
	 * Route to get a single ACL Entry by the ID TODO: This is a debugging route and
	 * should be removed later in production
	 * @param aclEntryId the id of the ACL Entry in the database
	 * @return the ACL Entry
	 */
	@GetMapping("/aclentry/{aclEntryId}")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public ACLEntry getACLEntry(@PathVariable Long aclEntryId) {
		logger.info("Trying to find an ACL Entry with id: " + aclEntryId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return aclEntryRepository.findById(aclEntryId).orElseThrow();
	}

	/**
	 * Route to update the ACL Entry manually TODO: This is a debugging route and should
	 * be removed later in production
	 * @param aclEntry the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/aclentry")
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLEntry updateACLEntry(@RequestBody @NotNull ACLEntry aclEntry) {
		logger.info("Trying to update the ACL Entry with id = " + aclEntry.getId() + " and the following content: "
				+ aclEntry);
		return aclEntryRepository.save(aclEntry);
	}

}
