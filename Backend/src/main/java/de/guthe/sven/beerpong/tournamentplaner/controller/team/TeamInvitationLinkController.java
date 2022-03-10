package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamInvitationLinkDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamInvitationLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamInvitationLinkController {

	private TeamInvitationLinkRepository teamInvitationLinkRepository;

	@Autowired
	public TeamInvitationLinkController(TeamInvitationLinkRepository teamInvitationLinkRepository) {
		this.teamInvitationLinkRepository = teamInvitationLinkRepository;
	}

	@GetMapping("/teaminvitationlink")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamInvitationLinkDTO> getTeamInvitationLinks() {
		return teamInvitationLinkRepository.findAll().stream().map(TeamInvitationLinkDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/teaminvitationlink/{teamInvitationLinkId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLinkDTO getTeamInvitationLink(@PathVariable Long teamInvitationLinkId) {
		return new TeamInvitationLinkDTO(teamInvitationLinkRepository.findById(teamInvitationLinkId).orElseThrow());
	}

}
