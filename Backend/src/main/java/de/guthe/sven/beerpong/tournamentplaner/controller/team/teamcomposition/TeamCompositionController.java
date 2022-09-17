package de.guthe.sven.beerpong.tournamentplaner.controller.team.teamcomposition;

import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.teamcomposition.TeamCompositionAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.teamcomposition.TeamCompositionStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.teamcomposition.TeamCompositionUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamcomposition.TeamCompositionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamcomposition.TeamCompositionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team/teamcomposition")
public class TeamCompositionController {

	private final TeamCompositionRepository teamCompositionRepository;

	private final TeamCompositionStatusRepository teamCompositionStatusRepository;

	private final TeamService teamService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param teamCompositionRepository jpa repository to handle all database queries
	 * directly in this controller regarding the team composition
	 * @param teamService service to handle all the transformations / database queries
	 * regarding the team
	 * @param teamCompositionStatusRepository jpa repository to handle all database
	 * queries directly in this controller regarding the team composition status
	 */
	@Autowired
	public TeamCompositionController(TeamCompositionRepository teamCompositionRepository, TeamService teamService,
			TeamCompositionStatusRepository teamCompositionStatusRepository) {
		this.teamCompositionRepository = teamCompositionRepository;
		this.teamService = teamService;
		this.teamCompositionStatusRepository = teamCompositionStatusRepository;
	}

	final private Logger logger = LoggerFactory.getLogger(TeamCompositionController.class);

	/**
	 * Route to return all Team Compositions TODO: Check if we want to change it to
	 * pagination
	 * @return all Team Compositions
	 */
	@GetMapping("/teamcomposition")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamCompositionDTO> getTeamCompositions() {
		logger.info("Fetch all Competition Teams");
		return teamCompositionRepository.findAll().stream().map(TeamCompositionDTO::new).collect(Collectors.toList());
	}

	/**
	 * Route to update the Team Compostition manually
	 * @param teamCompositionUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/teamcomposition")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamCompositionDTO updateTeamComposition(@RequestBody TeamCompositionUpdateDTO teamCompositionUpdateDTO) {
		logger.info("Trying to update the Team Composition with id = " + teamCompositionUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + teamCompositionUpdateDTO);
		return teamService.updateTeamComposition(teamCompositionUpdateDTO);
	}

	/**
	 * Route to add the Team Composition manually
	 * @param teamCompositionAddDTO Team Composition wrapped in data transfer object
	 * @return the created Team Composition
	 */
	@PostMapping("/teamcomposition")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamCompositionDTO addTeamComposition(@RequestBody TeamCompositionAddDTO teamCompositionAddDTO) {
		logger.info("Trying to save " + teamCompositionAddDTO);
		return teamService.addTeamComposition(teamCompositionAddDTO);
	}

	/**
	 * Route to get a single Team Composition by the ID
	 * @param teamCompositionId the id of the Team Composition in the database
	 * @return the Team Composition
	 */
	@GetMapping("/teamcomposition/{teamCompositionId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamCompositionDTO getTeamComposition(@PathVariable Long teamCompositionId) {
		logger.info("Trying to find a Competition Team with id: " + teamCompositionId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new TeamCompositionDTO(teamCompositionRepository.findById(teamCompositionId).orElseThrow());
	}

	/**
	 * Route to update the Team Composition Status manually TODO: Move to an separate
	 * Status Controller
	 * @param teamCompositionStatusUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/teamcompositionstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamCompositionStatusDTO> updateTeamCompositionStatus(
			@RequestBody TeamCompositionStatusUpdateDTO teamCompositionStatusUpdateDTO) {
		logger.info("Trying to update the Team Composition Status with id = " + teamCompositionStatusUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + teamCompositionStatusUpdateDTO);
		return teamService.updateTeamCompositionStatus(teamCompositionStatusUpdateDTO);
	}

	/**
	 * Route to return all Team Composition Status with Pagination TODO: Move to an
	 * separate Status Controller
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Team Composition Status
	 */
	@GetMapping("/teamcompositionstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getTeamCompositionStatus(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info(
				"Fetch page " + page + " of size " + size + "with filter '" + search + "' of Team Composition Status");

		Page<TeamCompositionStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM Team Composition Status
			pageRequest = teamCompositionStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM Team Composition Status WHERE Status LIKE search
			pageRequest = teamCompositionStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the Competition
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

}
