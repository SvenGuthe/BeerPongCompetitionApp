package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamStatusController {

	private final TeamStatusRepository teamStatusRepository;

	private final TeamService teamService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param teamStatusRepository jpa repository to handle all database queries directly
	 * in this controller regarding the team status
	 * @param teamService service to handle all the transformations / database queries
	 * regarding the team
	 */
	@Autowired
	public TeamStatusController(TeamStatusRepository teamStatusRepository, TeamService teamService) {
		this.teamStatusRepository = teamStatusRepository;
		this.teamService = teamService;
	}

	final private Logger logger = LoggerFactory.getLogger(TeamStatusController.class);

	/**
	 * Route to return all Team Status with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Team Status
	 */
	@GetMapping("/teamstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getTeamStatusPage(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Team Status");

		Page<TeamStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM Team Status
			pageRequest = teamStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM Team Status WHERE Status LIKE search
			pageRequest = teamStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the Team Status
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to update the Team Status manually
	 * @param teamStatusUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PostMapping("/teamstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamStatusDTO> updateTeamStatus(@RequestBody TeamStatusUpdateDTO teamStatusUpdateDTO) {
		logger.info("Trying to update the Team Status with id = " + teamStatusUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + teamStatusUpdateDTO);
		return teamService.updateTeamStatus(teamStatusUpdateDTO);
	}

	/**
	 * Route to get a single Team Status by the ID
	 * @param teamStatusId the id of the Team Status in the database
	 * @return the TeamStatus
	 */
	@GetMapping("/teamstatus/{teamStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamStatusDTO getTeamStatus(@PathVariable Long teamStatusId) {
		logger.info("Trying to find a Team Status with id: " + teamStatusId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new TeamStatusDTO(teamStatusRepository.findById(teamStatusId).orElseThrow());
	}

}
