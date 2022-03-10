package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.admin.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
	public PaginationDTO<EnumDTO> getRoles(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<Role> pageRequest;
		if (search.equals("")) {
			pageRequest = roleRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = roleRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(role -> new EnumDTO(
				role.getId(),
				role.getName()
		)).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);
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

	@DeleteMapping("/role")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public void deleteRole(@RequestBody Role role) {
		roleRepository.delete(role);
	}

}
