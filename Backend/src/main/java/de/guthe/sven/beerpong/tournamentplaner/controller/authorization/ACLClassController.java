package de.guthe.sven.beerpong.tournamentplaner.controller.authorization;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLClass;
import de.guthe.sven.beerpong.tournamentplaner.repository.authorization.ACLClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/acl")
public class ACLClassController {

	private ACLClassRepository aclClassRepository;

	@Autowired
	public ACLClassController(ACLClassRepository aclClassRepository) {
		this.aclClassRepository = aclClassRepository;
	}

	@PostMapping("/aclclass")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLClass addACLClass(@RequestBody ACLClass aclClass) {
		return aclClassRepository.save(aclClass);
	}

	@GetMapping("/aclclass")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public List<ACLClass> getACLClasses() {
		return aclClassRepository.findAll();
	}

	@GetMapping("/aclclass/{aclClassId}")
	@PreAuthorize("hasAuthority('READ_ACL_PRIVILEGE')")
	public ACLClass getACLClass(@PathVariable Long aclClassId) {
		return aclClassRepository.findById(aclClassId).get();
	}

	@PutMapping("/aclclass")
	@PreAuthorize("hasAuthority('WRITE_ACL_PRIVILEGE')")
	public ACLClass updateACLClass(@RequestBody ACLClass aclClass) {
		return aclClassRepository.save(aclClass);
	}

}
