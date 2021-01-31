package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/authentication")
public class RoleController {

	private RoleRepository roleRepository;

	@Autowired
	public RoleController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@PostMapping("/role")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public Role addRole(@RequestBody Role role) {
		return roleRepository.save(role);
	}

	@GetMapping("/role")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@GetMapping("/role/{roleId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public Role getRole(@PathVariable Long roleId) {
		return roleRepository.findById(roleId).orElseThrow();
	}

	@PutMapping("/role")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public Role updateRole(@RequestBody Role role) {
		return roleRepository.save(role);
	}

}
