package de.guthe.sven.beerpong.tournamentplaner.controller.team.teaminvitationlink;

import de.guthe.sven.beerpong.tournamentplaner.controller.competition.CompetitionTeamController;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamInvitationLinkAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teaminvitationlink.TeamInvitationLinkDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teaminvitationlink.TeamInvitationLinkRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team/teaminvitationlink")
public class TeamInvitationLinkController {

	private final TeamInvitationLinkRepository teamInvitationLinkRepository;

	private final TeamService teamService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param teamInvitationLinkRepository jpa repository to handle all database queries
	 * directly in this controller regarding the team invitation links
	 * @param teamService service to handle all the transformations / database queries
	 * regarding the teams
	 */
	@Autowired
	public TeamInvitationLinkController(TeamInvitationLinkRepository teamInvitationLinkRepository,
			TeamService teamService) {
		this.teamInvitationLinkRepository = teamInvitationLinkRepository;
		this.teamService = teamService;
	}

	final private Logger logger = LoggerFactory.getLogger(TeamInvitationLinkController.class);

	/**
	 * Route to return all Team Invitation Links TODO: Check if we want to change it to
	 * pagination
	 * @return all Team Invitation Links
	 */
	@GetMapping("/teaminvitationlink")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamInvitationLinkDTO> getTeamInvitationLinks() {
		logger.info("Fetch all Team Invitation Links");
		return teamInvitationLinkRepository.findAll().stream().map(TeamInvitationLinkDTO::new)
				.collect(Collectors.toList());
	}

	/**
	 * Route to get a single Team Invitation Link by the ID
	 * @param teamInvitationLinkId the id of the Team Invitation Link in the database
	 * @return the Team Invitation Link
	 */
	@GetMapping("/teaminvitationlink/{teamInvitationLinkId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLinkDTO getTeamInvitationLink(@PathVariable Long teamInvitationLinkId) {
		logger.info("Trying to find a Team Invitation Link with id: " + teamInvitationLinkId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new TeamInvitationLinkDTO(teamInvitationLinkRepository.findById(teamInvitationLinkId).orElseThrow());
	}

	/**
	 * Route to add the Invitation Link manually
	 * @param teamInvitationLinkAddDTO Team Invitation Link wrapped in data transfer
	 * object
	 * @return the created Team Invitation Link
	 */
	@PostMapping("/teaminvitationlink")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLinkDTO addTeamInvitationLink(@RequestBody TeamInvitationLinkAddDTO teamInvitationLinkAddDTO) {
		logger.info("Trying to save " + teamInvitationLinkAddDTO);
		return teamService.addTeamInvitationLink(teamInvitationLinkAddDTO);
	}

}
