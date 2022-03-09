package de.guthe.sven.beerpong.tournamentplaner.controller.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.admin.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition/registration")
public class RegistrationStatusController {

	private RegistrationStatusRepository registrationStatusRepository;

	@Autowired
	public RegistrationStatusController(RegistrationStatusRepository registrationStatusRepository) {
		this.registrationStatusRepository = registrationStatusRepository;
	}

	@GetMapping("/registrationstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getRegistrationStati(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<RegistrationStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = registrationStatusRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = registrationStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(registrationStatus -> new EnumDTO(
				registrationStatus.getId(),
				registrationStatus.getRegistrationStatusDescription().toString()
		)).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

	}

	@GetMapping("/registrationstatus/{registrationStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatus getRegistrationStatus(@PathVariable Long registrationStatusId) {
		return registrationStatusRepository.findById(registrationStatusId).orElseThrow();
	}

	@PostMapping("/registrationstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatus addRegistrationStatus(@RequestBody RegistrationStatus registrationStatus) {
		return registrationStatusRepository.save(registrationStatus);
	}

	@PutMapping("/registrationstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatus updateRegistrationStatus(@RequestBody RegistrationStatus registrationStatus) {
		return registrationStatusRepository.save(registrationStatus);
	}

	@DeleteMapping("/registrationstatus")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteRegistrationStatus(@RequestBody RegistrationStatus registrationStatus) {
		registrationStatusRepository.delete(registrationStatus);
	}

}
