package de.guthe.sven.beerpong.tournamentplaner.controller.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.userstatus.UserStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserStatusController {

	private final UserStatusRepository userStatusRepository;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param userStatusRepository jpa repository to handle all database queries directly
	 * in this controller regarding the user status
	 */
	@Autowired
	public UserStatusController(UserStatusRepository userStatusRepository) {
		this.userStatusRepository = userStatusRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(UserStatusController.class);

	/**
	 * Route to add the User Status manually
	 * @param userStatus User Status TODO: Check if we need a separate DTO here
	 * @return the created User Status TODO: Check if we need a separate DTO here
	 */
	@PostMapping("/userstatus")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public UserStatus addUserStatus(@RequestBody UserStatus userStatus) {
		logger.info("Trying to save " + userStatus);
		return userStatusRepository.save(userStatus);
	}

	/**
	 * Route to return all User Status with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the User Status
	 */
	@GetMapping("/userstatus")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getUserStatusPage(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of User Status");

		Page<UserStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM User Status
			pageRequest = userStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM User Status WHERE Status LIKE search
			pageRequest = userStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the User Status
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single User Status by the ID TODO: Change to a single object
	 * @param userStatusId the id of the User Status in the database
	 * @return the User Status
	 */
	@GetMapping("/userstatus/{userStatusId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public List<UserStatusDTO> getUserStatus(@PathVariable Long userStatusId) {
		logger.info("Trying to find a User Status with id: " + userStatusId);

		Optional<UserStatus> userStatus = userStatusRepository.findById(userStatusId);

		if (userStatus.isEmpty()) {
			throw new RuntimeException("User Status not present with given id " + userStatusId);
		}
		else {
			return userStatus.get().getUserStatusHistories().stream().map(UserStatusDTO::new)
					.collect(Collectors.toList());
		}
	}

}
