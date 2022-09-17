package de.guthe.sven.beerpong.tournamentplaner.controller.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionplayer.CompetitionPlayerStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionplayer.CompetitionPlayerStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionplayer.CompetitionPlayerStatusRepository;
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
@RequestMapping("/competition/competitionplayer")
public class CompetitionPlayerStatusController {

	private final CompetitionPlayerStatusRepository competitionPlayerStatusRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionPlayerStatusRepository jpa repository to handle all database
	 * queries directly in this controller regarding the competition player
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public CompetitionPlayerStatusController(CompetitionPlayerStatusRepository competitionPlayerStatusRepository,
			CompetitionService competitionService) {
		this.competitionPlayerStatusRepository = competitionPlayerStatusRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(CompetitionPlayerStatusController.class);

	/**
	 * Route to return all Competition Player Status with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Competition Player Status
	 */
	@GetMapping("/competitionplayerstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getCompetitionPlayerStatusPage(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info(
				"Fetch page " + page + " of size " + size + "with filter '" + search + "' of Competition Admin Status");

		Page<CompetitionPlayerStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM CompetitionPlayerStatus
			pageRequest = competitionPlayerStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM CompetitionPlayerStatus WHERE Status LIKE search
			pageRequest = competitionPlayerStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Enum Data Transfer Objects
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		logger.info("Return the following page: " + data);

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single Competition Player Status by the ID
	 * @param competitionPlayerStatusId the id of the Competition Player Status in the
	 * database
	 * @return the Competition Player Status
	 */
	@GetMapping("/competitionplayerstatus/{competitionPlayerStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerStatusDTO getCompetitionPlayerStatus(@PathVariable Long competitionPlayerStatusId) {
		logger.info("Trying to find a Competition Player Status with id: " + competitionPlayerStatusId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new CompetitionPlayerStatusDTO(
				competitionPlayerStatusRepository.findById(competitionPlayerStatusId).orElseThrow());
	}

	/**
	 * Route to update the Competition Player Status manually
	 * @param competitionPlayerStatusUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/competitionplayerstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Collection<CompetitionPlayerStatusDTO> updateCompetitionPlayerStatus(
			@RequestBody @NotNull CompetitionPlayerStatusUpdateDTO competitionPlayerStatusUpdateDTO) {
		logger.info(
				"Trying to update the Competition Player Status with id = " + competitionPlayerStatusUpdateDTO.getId()
						+ " and the following content (Wrapped in a DTO): " + competitionPlayerStatusUpdateDTO);
		return competitionService.updateCompetitionPlayerStatus(competitionPlayerStatusUpdateDTO);
	}

}
