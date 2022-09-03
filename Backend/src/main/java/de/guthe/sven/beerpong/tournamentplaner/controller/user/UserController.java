package de.guthe.sven.beerpong.tournamentplaner.controller.user;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.ConfirmationTokenAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.user.UserUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.ConfirmationTokenDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.user.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.user.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserRepository userRepository;

	private final UserService userService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param userRepository repository to handle all database queries directly in this
	 * controller regarding the user
	 * @param userService service to handle all the transformations / database queries
	 * regarding the user
	 */
	@Autowired
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	final private Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * Route to add the User manually
	 * @param user the user TODO: Check if we need a separate DTO
	 * @return the created user TODO: Check if we need a separate DTO
	 */
	@PostMapping("/user")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public User addUser(@RequestBody User user) {
		logger.info("Trying to save " + user);
		return userRepository.save(user);
	}

	/**
	 * Route to add the Confirmation Token manually TODO: Check if we want to move the
	 * function to an separate controller
	 * @param confirmationTokenAddDTO the Confirmation Token which should be added wrapped
	 * in a Data Transfer Object
	 * @return the created Confirmation Token wrapped in a Data Transfer Object
	 */
	@PostMapping("/confirmationtoken")
	@PreAuthorize("hasAuthority('ADMIN_AUTHENTICATION_PRIVILEGE')")
	public ConfirmationTokenDTO addConfirmToken(@RequestBody ConfirmationTokenAddDTO confirmationTokenAddDTO) {
		logger.info("Trying to save " + confirmationTokenAddDTO);
		return userService.addConfirmationToken(confirmationTokenAddDTO);
	}

	/**
	 * Route to toggle the disable / enable the Confirmation Token manually TODO: Check if
	 * we want to move the function to an separate controller
	 * @param confirmationTokenDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/confirmationtoken")
	@PreAuthorize("hasAuthority('ADMIN_AUTHENTICATION_PRIVILEGE')")
	public ConfirmationTokenDTO toggleConfirmToken(@RequestBody ConfirmationTokenDTO confirmationTokenDTO) {
		logger.info("Trying to update the Confirmation Token with id = " + confirmationTokenDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + confirmationTokenDTO);
		return userService.toggleConfirmationToken(confirmationTokenDTO);
	}

	/**
	 * Route to update the User manually
	 * @param userUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/user")
	@PreAuthorize("hasAuthority('ADMIN_AUTHENTICATION_PRIVILEGE')")
	public UserDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
		logger.info("Trying to update the User with id = " + userUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + userUpdateDTO);
		return userService.updateUser(userUpdateDTO);
	}

	/**
	 * Route to return all User with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the User
	 */
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public PaginationDTO<UserDTO> getUsers(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Users");

		Page<User> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM User
			pageRequest = userRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM User WHERE GamerTag LIKE search
			pageRequest = userRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the User
		List<UserDTO> data = pageRequest.stream().map(UserDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single User by the ID
	 * @param userId the id of the User in the database
	 * @return the User
	 */
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public UserDetailDTO getUser(@PathVariable Long userId) {
		logger.info("Trying to find a User with id: " + userId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return userService.transformUserToUserDetailDTO(userRepository.findById(userId).orElseThrow());
	}

}
