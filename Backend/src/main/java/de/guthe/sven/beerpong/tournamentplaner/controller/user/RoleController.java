package de.guthe.sven.beerpong.tournamentplaner.controller.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.Role;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param roleRepository jpa repository to handle all database queries directly in
	 * this controller regarding the roles
	 */
	@Autowired
	public RoleController(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(RoleController.class);

	/**
	 * Route to add the Role manually
	 * @param role the role TODO: Check if we need a separate DTO
	 * @return created role TODO: Check if we need a separate DTO
	 */
	@PostMapping("/role")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public Role addRole(@RequestBody Role role) {
		logger.info("Trying to save " + role);
		return roleRepository.save(role);
	}

	/**
	 * Route to return all Roles with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Roles
	 */
	@GetMapping("/role")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getRoles(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Roles");

		Page<Role> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM Roles
			pageRequest = roleRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM Roles WHERE Role LIKE search
			pageRequest = roleRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the Roles
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);
	}

}
