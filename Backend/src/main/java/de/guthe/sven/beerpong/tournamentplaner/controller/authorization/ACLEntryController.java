package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLEntry;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLEntryController {

	private ACLEntryRepository aclEntryRepository;

	@Autowired
	public ACLEntryController(ACLEntryRepository aclEntryRepository) {
		this.aclEntryRepository = aclEntryRepository;
	}

	@PostMapping("/aclentry")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLEntry addACLEntry(@RequestBody ACLEntry aclEntry) {
		return aclEntryRepository.save(aclEntry);
	}

	@GetMapping("/aclentry")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public List<ACLEntry> getACLEntries() {
		return aclEntryRepository.findAll();
	}

	@GetMapping("/aclentry/{aclEntryId}")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public ACLEntry getACLEntry(@PathVariable Long aclEntryId) {
		return aclEntryRepository.findById(aclEntryId).orElseThrow();
	}

	@PutMapping("/aclentry")
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLEntry updateACLEntry(@RequestBody ACLEntry aclEntry) {
		return aclEntryRepository.save(aclEntry);
	}

}
