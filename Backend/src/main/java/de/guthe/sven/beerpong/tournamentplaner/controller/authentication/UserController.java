package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class UserController {

	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/user")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public PaginationDTO<UserDTO> getUsers(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<User> pageRequest;
		if (search.equals("")) {
			pageRequest = userRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = userRepository.findAll(search, PageRequest.of(page, size));
		}

		List<UserDTO> data = pageRequest.stream().map(UserDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

	}

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public UserDTO getUser(@PathVariable Long userId) {
		return new UserDTO(userRepository.findById(userId).orElseThrow());
	}

}
