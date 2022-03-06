package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.admin.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentication")
public class UserStatusController {

	private UserStatusRepository userStatusRepository;

	@Autowired
	public UserStatusController(UserStatusRepository userStatusRepository) {
		this.userStatusRepository = userStatusRepository;
	}

	@PostMapping("/userstatus")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public UserStatus addUserStatusRepository(@RequestBody UserStatus userStatus) {
		return userStatusRepository.save(userStatus);
	}

	// I know that the plural of status = status but I need a unique function name
	@GetMapping("/userstatus")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public List<EnumDTO> getUserStati() {
		return userStatusRepository.findAll().stream().map(EnumDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/userstatus/{userStatusId}")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public UserStatus getUserStatus(@PathVariable Long userStatusId) {
		return userStatusRepository.findById(userStatusId).orElseThrow();
	}

	@PutMapping("/userstatus")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public UserStatus updateUserStatus(@RequestBody UserStatus userStatus) {
		return userStatusRepository.save(userStatus);
	}

	@DeleteMapping("/userstatus")
	@PreAuthorize("hasAuthority('WRITE_AUTHENTICATION_PRIVILEGE')")
	public void deleteUserStatus(@RequestBody UserStatus userStatus) {
		userStatusRepository.delete(userStatus);
	}

}
