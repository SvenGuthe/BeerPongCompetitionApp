package de.guthe.sven.beerpong.tournamentplaner.controller.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.registration.RegistrationStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.RegistrationStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition/registration")
public class RegistrationStatusController {

	private final RegistrationStatusRepository registrationStatusRepository;

	private final CompetitionService competitionService;

	@Autowired
	public RegistrationStatusController(RegistrationStatusRepository registrationStatusRepository,
			CompetitionService competitionService) {
		this.registrationStatusRepository = registrationStatusRepository;
		this.competitionService = competitionService;
	}

	@GetMapping("/registrationstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getRegistrationStati(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		Page<RegistrationStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = registrationStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			pageRequest = registrationStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	@GetMapping("/registrationstatus/{registrationStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatusDTO getRegistrationStatus(@PathVariable Long registrationStatusId) {
		return new RegistrationStatusDTO(registrationStatusRepository.findById(registrationStatusId).orElseThrow());
	}

	@PutMapping("/registrationstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Collection<RegistrationStatusDTO> updateRegistrationStatus(
			@RequestBody RegistrationStatusUpdateDTO registrationStatusUpdateDTO) {
		return competitionService.updateRegistrationStatus(registrationStatusUpdateDTO);
	}

}
