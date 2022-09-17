package de.guthe.sven.beerpong.tournamentplaner.controller.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.PrivilegeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class PrivilegeController {

	private final PrivilegeRepository privilegeRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param privilegeRepository jpa repository to handle all database queries directly
	 * in this controller regarding the privileges
	 */
	@Autowired
	public PrivilegeController(PrivilegeRepository privilegeRepository) {
		this.privilegeRepository = privilegeRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(PrivilegeController.class);

	/**
	 * Route to add the Privilege manually
	 * @param privilege the privilege TODO: Check if we need a separate DTO
	 * @return the created privilege TODO: Check if we need a separate DTO
	 */
	@PostMapping("/privilege")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public Privilege addPrivilege(@RequestBody Privilege privilege) {
		logger.info("Trying to save " + privilege);
		return privilegeRepository.save(privilege);
	}

	/**
	 * Route to get a single Privilege by the ID
	 * @param privilegeId the id of the Privilege in the database
	 * @return the Privilege
	 */
	@GetMapping("/privilege/{privilegeId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public Privilege getPrivilege(@PathVariable Long privilegeId) {
		logger.info("Trying to find a Privilege with id: " + privilegeId);

		Optional<Privilege> privilege = privilegeRepository.findById(privilegeId);

		if (privilege.isEmpty()) {
			throw new RuntimeException("Privilege not present with given id " + privilegeId);
		}
		else {
			return privilege.get();
		}
	}

	/**
	 * Route to return all Privileges with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Privileges
	 */
	@GetMapping("/privilege")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getPrivileges(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Privileges");

		Page<Privilege> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM Privilege
			pageRequest = privilegeRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM Privilege WHERE Privilege LIKE search
			pageRequest = privilegeRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the Privileges
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);
	}

}
