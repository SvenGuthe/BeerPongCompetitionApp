package de.guthe.sven.beerpong.tournamentplaner.controller.authentication;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.admin.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.authentication.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	@GetMapping("/userstatus")
	@PreAuthorize("hasAuthority('READ_AUTHENTICATION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getUserStati(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<UserStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = userStatusRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = userStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(userStatus -> new EnumDTO(
				userStatus.getUserStatusId(),
				userStatus.getUserStatus().toString()
		)).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

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
