package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.ConfirmationTokenAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.authentication.UserUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.ConfirmationTokenDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.authentication.UserDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.authentication.UserService;
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

	private UserService userService;

	@Autowired
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	@PostMapping("/user")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PostMapping("/confirmationtoken")
    @PreAuthorize("hasAuthority('ADMIN_AUTHENTICATION_PRIVILEGE')")
    public ConfirmationTokenDTO addConfirmToken(@RequestBody ConfirmationTokenAddDTO confirmationTokenAddDTO) {
        return userService.addConfirmationToken(confirmationTokenAddDTO);
    }

	@PutMapping("/user")
	@PreAuthorize("hasAuthority('ADMIN_AUTHENTICATION_PRIVILEGE')")
	public UserDTO updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
		return userService.updateUser(userUpdateDTO);
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
	public UserDetailDTO getUser(@PathVariable Long userId) {
		return userService.transformUserToUserDetailDTO(userRepository.findById(userId).orElseThrow());
	}

}
