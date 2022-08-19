package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamInvitationLinkAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamInvitationLinkDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamInvitationLinkRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamInvitationLinkController {

	private final TeamInvitationLinkRepository teamInvitationLinkRepository;

	private final TeamService teamService;

	@Autowired
	public TeamInvitationLinkController(TeamInvitationLinkRepository teamInvitationLinkRepository,
			TeamService teamService) {
		this.teamInvitationLinkRepository = teamInvitationLinkRepository;
		this.teamService = teamService;
	}

	@GetMapping("/teaminvitationlink")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamInvitationLinkDTO> getTeamInvitationLinks() {
		return teamInvitationLinkRepository.findAll().stream().map(TeamInvitationLinkDTO::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/teaminvitationlink/{teamInvitationLinkId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLinkDTO getTeamInvitationLink(@PathVariable Long teamInvitationLinkId) {
		return new TeamInvitationLinkDTO(teamInvitationLinkRepository.findById(teamInvitationLinkId).orElseThrow());
	}

	@PostMapping("/teaminvitationlink")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLinkDTO addTeamInvitationLink(@RequestBody TeamInvitationLinkAddDTO teamInvitationLinkAddDTO) {
		return teamService.addTeamInvitationLink(teamInvitationLinkAddDTO);
	}

}
