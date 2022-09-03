package de.guthe.sven.beerpong.tournamentplaner.controller.competition.competitionadmin;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionAdminStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionadmin.CompetitionAdminStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionadmin.CompetitionAdminStatusRepository;
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
@RequestMapping("/competition/competitionadmin")
public class CompetitionAdminStatusController {

	private final CompetitionAdminStatusRepository competitionAdminStatusRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionAdminStatusRepository jpa repository to handle all database
	 * queries directly in this controller regarding the competition admin status
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public CompetitionAdminStatusController(CompetitionAdminStatusRepository competitionAdminStatusRepository,
			CompetitionService competitionService) {
		this.competitionAdminStatusRepository = competitionAdminStatusRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(CompetitionAdminStatusController.class);

	/**
	 * Route to return all Competition Admin Status with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Competition Admin Status
	 */
	@GetMapping("/competitionadminstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getCompetitionAdminStatusPage(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info(
				"Fetch page " + page + " of size " + size + "with filter '" + search + "' of Competition Admin Status");

		Page<CompetitionAdminStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM CompetitionAdminStatus
			pageRequest = competitionAdminStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM CompetitionAdminStatus WHERE Status LIKE search
			pageRequest = competitionAdminStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Enum Data Transfer Objects
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		logger.info("Return the following page: " + data);

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single Competition Admin Status by the ID
	 * @param competitionAdminStatusId the id of the Competition Admin Status in the
	 * database
	 * @return the Competition Admin Status
	 */
	@GetMapping("/competitionadminstatus/{competitionAdminStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatusDTO getCompetitionAdminStatus(@PathVariable Long competitionAdminStatusId) {
		logger.info("Trying to find a Competition Admin Status with id: " + competitionAdminStatusId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new CompetitionAdminStatusDTO(
				competitionAdminStatusRepository.findById(competitionAdminStatusId).orElseThrow());
	}

	/**
	 * Route to update the Competition Admin Status manually
	 * @param competitionAdminStatusUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/competitionadminstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Collection<CompetitionAdminStatusDTO> updateCompetitionAdminStatus(
			@RequestBody @NotNull CompetitionAdminStatusUpdateDTO competitionAdminStatusUpdateDTO) {
		logger.info("Trying to update the Competition Admin Status with id = " + competitionAdminStatusUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + competitionAdminStatusUpdateDTO);
		return competitionService.updateCompetitionAdminStatus(competitionAdminStatusUpdateDTO);
	}

}
