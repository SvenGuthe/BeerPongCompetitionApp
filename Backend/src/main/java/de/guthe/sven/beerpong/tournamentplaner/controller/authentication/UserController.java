package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.ChangeUserRoleDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.UserDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Role;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.RoleRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class UserController {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	@Autowired
	public UserController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@PostMapping("/user")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public User addUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	/*
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	 */

	@GetMapping("/user")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public List<UserDetailDTO> getUsers() {
		return userRepository.findAll().stream().map(UserDetailDTO::new).collect(Collectors.toList());
	}

	@PutMapping("/user")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public UserDetailDTO changeUserRole(@RequestBody ChangeUserRoleDTO changeUserRoleDTO) {
		User user = userRepository.findById(changeUserRoleDTO.getId()).get();
		Role role = roleRepository.findByName(changeUserRoleDTO.getRole());

		Collection<Role> userRoles = user.getRoles();

		if (userRoles.contains(role) && !changeUserRoleDTO.getValue()) {
			userRoles.remove(role);
		} else if (!userRoles.contains(role) && changeUserRoleDTO.getValue()) {
			userRoles.add(role);
		}

		user.setRoles(userRoles);
		userRepository.save(user);

		return new UserDetailDTO(user);
	}

	@GetMapping("/user/{userId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public UserDetailDTO getUser(@PathVariable Long userId) {
		return new UserDetailDTO(userRepository.findById(userId).orElseThrow());
	}

	/*
	@PutMapping("/user")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public User updateUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	 */

	@DeleteMapping("/user")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public void deleteUser(@RequestBody User user) {
		userRepository.delete(user);
	}

}
