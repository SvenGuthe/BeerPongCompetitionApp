package de.guthe.sven.beerpong.tournamentplaner.controller.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.Role;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class RoleController {

	private final RoleRepository roleRepository;

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
	public PaginationDTO<EnumDTO> getRoles(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		Page<Role> pageRequest;
		if (search.equals("")) {
			pageRequest = roleRepository.findAll(PageRequest.of(page, size));
		}
		else {
			pageRequest = roleRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);
	}

}
