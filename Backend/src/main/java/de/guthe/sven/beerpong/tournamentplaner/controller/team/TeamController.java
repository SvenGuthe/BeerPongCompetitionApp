package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.team.TeamPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamController {

	private final ACLService aclService;

	private final TeamRepository teamRepository;

	private final TeamService teamService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclService service to set privileges for objects
	 * @param teamRepository jpa repository to handle all database queries directly in
	 * this controller regarding the teams
	 * @param teamService service to handle all the transformations / database queries
	 * regarding the teams
	 */
	@Autowired
	public TeamController(ACLService aclService, TeamRepository teamRepository, TeamService teamService) {
		this.aclService = aclService;
		this.teamRepository = teamRepository;
		this.teamService = teamService;
	}

	final private Logger logger = LoggerFactory.getLogger(TeamController.class);

	/**
	 * Route to return all Teams with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Teams
	 */
	@GetMapping("/team")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public PaginationDTO<TeamDTO> getTeams(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Teams");

		Page<Team> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM Teams
			pageRequest = teamRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM Competition WHERE TeamName LIKE search
			pageRequest = teamRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Data Transfer Objects for the Teams
		List<TeamDTO> data = pageRequest.stream().map(TeamDTO::new).collect(Collectors.toList());

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single Team by the ID
	 * @param teamId the id of the Team in the database
	 * @return the Team
	 */
	@GetMapping("/team/{teamId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamDetailDTO getTeam(@PathVariable Long teamId) {
		logger.info("Trying to find a Team with id: " + teamId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return teamService.transformTeamToTeamDetailDTO(teamRepository.findById(teamId).orElseThrow());
	}

	/**
	 * Route to update the Team manually
	 * @param teamUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/team")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamDTO updateTeam(@RequestBody TeamUpdateDTO teamUpdateDTO) {
		logger.info("Trying to update the Team with id = " + teamUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + teamUpdateDTO);
		return teamService.updateTeam(teamUpdateDTO);
	}

	/**
	 * Route to add a new Team and set ownerPermissions to the one who created it
	 * @param team the team which should be added TODO: Check if we need a separate DTO
	 * here
	 * @return the created team TODO: Check if we need a separate DTO here
	 */
	@PostMapping("/team")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public Team addTeam(@RequestBody Team team) {
		// Get the current user and user the name (E-Mail) as the PrincipalSid
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		// Initialize the team permissions and add owner Permissions to the current user
		Map<Sid, List<Permission>> initialTeamPermissions = TeamPermissions.initialTeamPermissions;
		initialTeamPermissions.put(sidCreator, TeamPermissions.ownerPermissions);

		// Store the team and set the privileges
		teamRepository.save(team);
		aclService.setPrivileges(team, initialTeamPermissions);
		return team;
	}

	// TODO: Remove hasPermission later -> currently just a placeholder, the modifications
	// on raw-database
	// TODO: entries are just allowed with ADMIN Privileges
	// @PutMapping("/team")
	// @PreAuthorize("hasPermission(#team, 'UPDATE_TEAM') or
	// hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	// public Team updateTeam(@RequestBody Team team) {
	// return teamRepository.save(team);
	// }

}
