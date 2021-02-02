package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamInvitationLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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
	public List<TeamInvitationLink> getTeamInvitationLinks() {
		return teamInvitationLinkRepository.findAll();
	}

	@GetMapping("/teaminvitationlink/{teamInvitationLinkId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLink getTeamInvitationLink(@PathVariable Long teamInvitationLinkId) {
		return teamInvitationLinkRepository.findById(teamInvitationLinkId).orElseThrow();
	}

	@PostMapping("/teaminvitationlink")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLink addTeamInvitationLink(@RequestBody TeamInvitationLink teamInvitationLink) {
		return teamInvitationLinkRepository.save(teamInvitationLink);
	}

	// TODO: Remove hasPermission later -> currently just a placeholder, the modifications
	// on raw-database
	// TODO: entries are just allowed with ADMIN Privileges
	@PutMapping("/teaminvitationlink")
	@PreAuthorize("hasPermission(#teamInvitationLink, 'UPDATE_TEAM_INVITATION_LINK') or hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamInvitationLink updateTeamInvitationLink(@RequestBody TeamInvitationLink teamInvitationLink) {
		return teamInvitationLinkRepository.save(teamInvitationLink);
	}

	// TODO: Remove hasPermission later -> currently just a placeholder, the modifications
	// on raw-database
	// TODO: entries are just allowed with ADMIN Privileges
	@DeleteMapping("/teaminvitationlink")
	@Transactional
	@PreAuthorize("hasPermission(#teamInvitationLink, 'DELETE_TEAM_INVITATION_LINK') or hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public void deleteTeamInvitationLink(@RequestBody TeamInvitationLink teamInvitationLink) {
		teamInvitationLinkRepository.delete(teamInvitationLink);
	}

}
