package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/authentication")
public class PrivilegeController {

	private PrivilegeRepository privilegeRepository;

	@Autowired
	public PrivilegeController(PrivilegeRepository privilegeRepository) {
		this.privilegeRepository = privilegeRepository;
	}

	@PostMapping("/privilege")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public Privilege addPrivilege(@RequestBody Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

	@GetMapping("/privilege/{privilegeId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public Privilege getPrivilege(@PathVariable Long privilegeId) {
		return privilegeRepository.findById(privilegeId).orElseThrow();
	}

	@GetMapping("/privilege")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public List<Privilege> getPrivileges() {
		return privilegeRepository.findAll();
	}

	@PutMapping("/privilege")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public Privilege updatePrivilege(@RequestBody Privilege privilege) {
		return privilegeRepository.save(privilege);
	}

}
