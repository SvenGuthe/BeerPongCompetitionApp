package de.guthe.sven.beerpong.tournamentplaner.controller.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.registration.RegistrationStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.registration.RegistrationStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param registrationStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the registration status
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public RegistrationStatusController(RegistrationStatusRepository registrationStatusRepository,
			CompetitionService competitionService) {
		this.registrationStatusRepository = registrationStatusRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(RegistrationStatusController.class);

	/**
	 * Route to return all Registration Status with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Registration Status
	 */
	@GetMapping("/registrationstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getRegistrationStatusPage(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Billing Status");

		Page<RegistrationStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM RegistrationStatus
			pageRequest = registrationStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM RegistrationStatus WHERE Status LIKE search
			pageRequest = registrationStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Enum Data Transfer Objects
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		logger.info("Return the following page: " + data);

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single Registration Status by the ID
	 * @param registrationStatusId the id of the Registration Status in the database
	 * @return the Registration Status
	 */
	@GetMapping("/registrationstatus/{registrationStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public RegistrationStatusDTO getRegistrationStatus(@PathVariable Long registrationStatusId) {
		logger.info("Trying to find a Registration Status with id: " + registrationStatusId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new RegistrationStatusDTO(registrationStatusRepository.findById(registrationStatusId).orElseThrow());
	}

	/**
	 * Route to update the Registration Status manually
	 * @param registrationStatusUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/registrationstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Collection<RegistrationStatusDTO> updateRegistrationStatus(
			@RequestBody @NotNull RegistrationStatusUpdateDTO registrationStatusUpdateDTO) {
		logger.info("Trying to update the Registration Status with id = " + registrationStatusUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + registrationStatusUpdateDTO);
		return competitionService.updateRegistrationStatus(registrationStatusUpdateDTO);
	}

}
