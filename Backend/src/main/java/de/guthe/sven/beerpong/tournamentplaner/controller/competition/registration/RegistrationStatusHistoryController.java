package de.guthe.sven.beerpong.tournamentplaner.controller.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition/registration")
public class RegistrationStatusHistoryController {

	private RegistrationStatusHistoryRepository registrationStatusHistoryRepository;

	@Autowired
	public RegistrationStatusHistoryController(
			RegistrationStatusHistoryRepository registrationStatusHistoryRepository) {
		this.registrationStatusHistoryRepository = registrationStatusHistoryRepository;
	}

	@GetMapping("/registrationstatushistory")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<RegistrationStatusHistory> getRegistrationStatusHistories() {
		return registrationStatusHistoryRepository.findAll();
	}

	@GetMapping("/registrationstatushistory/{registrationStatusHistoryId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatusHistory getRegistrationStatusHistory(@PathVariable Long registrationStatusHistoryId) {
		return registrationStatusHistoryRepository.findById(registrationStatusHistoryId).orElseThrow();
	}

	@PostMapping("/registrationstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatusHistory addRegistrationStatusHistory(
			@RequestBody RegistrationStatusHistory registrationStatusHistory) {
		return registrationStatusHistoryRepository.save(registrationStatusHistory);
	}

	@PutMapping("/registrationstatushistory")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatusHistory updateRegistrationStatusHistory(
			@RequestBody RegistrationStatusHistory registrationStatusHistory) {
		return registrationStatusHistoryRepository.save(registrationStatusHistory);
	}

	@DeleteMapping("/registrationstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteRegistrationStatusHistory(@RequestBody RegistrationStatusHistory registrationStatusHistory) {
		registrationStatusHistoryRepository.delete(registrationStatusHistory);
	}

}
