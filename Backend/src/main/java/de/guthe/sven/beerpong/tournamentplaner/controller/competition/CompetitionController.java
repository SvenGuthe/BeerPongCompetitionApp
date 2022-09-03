package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.competition.CompetitionPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

	private final ACLService aclService;

	private final CompetitionRepository competitionRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclService service to set privileges for objects
	 * @param competitionRepository jpa repository to handle all database queries directly
	 * in this controller regarding the competition
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public CompetitionController(ACLService aclService, CompetitionRepository competitionRepository,
			CompetitionService competitionService) {
		this.aclService = aclService;
		this.competitionRepository = competitionRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(CompetitionController.class);

	/**
	 * Route to return all Competition with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Competition
	 */
	@GetMapping("/competition")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<CompetitionDTO> getCompetitions(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Competitions");

		Page<Competition> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM Competition
			pageRequest = competitionRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM Competition WHERE CompetitionName LIKE search
			pageRequest = competitionRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the Competition
		List<CompetitionDTO> data = pageRequest.stream().map(CompetitionDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single Competition by the ID
	 * @param competitionId the id of the Competition in the database
	 * @return the Competition
	 */
	@GetMapping("/competition/{competitionId}")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionDetailDTO getCompetition(@PathVariable Long competitionId) {
		logger.info("Trying to find a Competition with id: " + competitionId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return competitionService.getCompetitionDetail(competitionId);
	}

	/**
	 * Route to update the Competition manually
	 * @param competitionUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/competition")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionDTO updateCompetition(@RequestBody @NotNull CompetitionUpdateDTO competitionUpdateDTO) {
		logger.info("Trying to update the Competition with id = " + competitionUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + competitionUpdateDTO);
		return competitionService.updateCompetition(competitionUpdateDTO);
	}

	/**
	 * Route to add a new competition and set ownerPermissions to the one who created it
	 * @param competition the competition which should be added TODO: Check if we need a
	 * separate DTO here
	 * @return the created competition TODO: Check if we need a separate DTO here
	 */
	@PostMapping("/competition")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Competition addCompetition(@RequestBody Competition competition) {
		// Get the current user and user the name (E-Mail) as the PrincipalSid
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		// Initialize the competition permissions and add owner Permissions to the current
		// user
		Map<Sid, List<Permission>> initialCompetitionPermissions = CompetitionPermissions.initialCompetitionPermissions;
		initialCompetitionPermissions.put(sidCreator, CompetitionPermissions.ownerPermissions);

		// Store the competition and set the privileges
		competitionRepository.save(competition);
		aclService.setPrivileges(competition, initialCompetitionPermissions);
		return competition;
	}

}
