package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionstatus.CompetitionStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionstatus.CompetitionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
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
@RequestMapping("/competition")
public class CompetitionStatusController {

	private final CompetitionStatusRepository competitionStatusRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition status
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public CompetitionStatusController(CompetitionStatusRepository competitionStatusRepository,
			CompetitionService competitionService) {
		this.competitionStatusRepository = competitionStatusRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(CompetitionStatusController.class);

	/**
	 * Route to return all CompetitionStatus with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Competition Status
	 */
	@GetMapping("/competitionstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getCompetitionStatusPage(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Competition Status");

		Page<CompetitionStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM CompetitionStatus
			pageRequest = competitionStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM CompetitionStatus WHERE Status LIKE search
			pageRequest = competitionStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the Competition
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);
	}

	/**
	 * Route to get a single Competition Status by the ID
	 * @param competitionStatusId the id of the Competition Status in the database
	 * @return the Competition Status
	 */
	@GetMapping("/competitionstatus/{competitionStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatusDTO getCompetitionStatus(@PathVariable Long competitionStatusId) {
		logger.info("Trying to find a Competition Status with id: " + competitionStatusId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new CompetitionStatusDTO(competitionStatusRepository.findById(competitionStatusId).orElseThrow());
	}

	/**
	 * Route to update the Competition Status manually
	 * @param competitionStatusUpdateDTO he updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/competitionstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Collection<CompetitionStatusDTO> updateCompetitionStatus(
			@RequestBody CompetitionStatusUpdateDTO competitionStatusUpdateDTO) {
		logger.info("Trying to update the Competition Status with id = " + competitionStatusUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + competitionStatusUpdateDTO);
		return competitionService.updateCompetitionStatus(competitionStatusUpdateDTO);
	}

}
