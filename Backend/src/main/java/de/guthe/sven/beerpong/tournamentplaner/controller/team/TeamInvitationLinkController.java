package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.team.TeamInvitationLinkPermissions;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamInvitationLinkRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
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

@RestController
@RequestMapping("/team")
public class TeamInvitationLinkController {

	private ACLService aclService;

	private TeamInvitationLinkRepository teamInvitationLinkRepository;

	@Autowired
	public TeamInvitationLinkController(ACLService aclService,
			TeamInvitationLinkRepository teamInvitationLinkRepository) {
		this.aclService = aclService;
		this.teamInvitationLinkRepository = teamInvitationLinkRepository;
	}

	@GetMapping("/teaminvitationlink")
	@PostFilter("hasAuthority('ADMIN_TEAM_INVITATION_LINK_PRIVILEGE')")
	public List<TeamInvitationLink> getTeamInvitationLinks() {
		return teamInvitationLinkRepository.findAll();
	}

	@GetMapping("/teaminvitationlink/{teamInvitationLinkId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_INVITATION_LINK_PRIVILEGE')")
	public TeamInvitationLink getTeamInvitationLink(@PathVariable Long teamInvitationLinkId) {
		return teamInvitationLinkRepository.findById(teamInvitationLinkId).orElseThrow();
	}

	@PostMapping("/teaminvitationlink")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_INVITATION_LINK_PRIVILEGE')")
	public TeamInvitationLink addTeamInvitationLink(@RequestBody TeamInvitationLink teamInvitationLink) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		Map<Sid, List<Permission>> initialTeamInvitationLinkPermissions = TeamInvitationLinkPermissions.initialTeamInvitationLinkPermissions;
		initialTeamInvitationLinkPermissions.put(sidCreator, TeamInvitationLinkPermissions.ownerPermissions);

		teamInvitationLinkRepository.save(teamInvitationLink);
		aclService.setPrivileges(teamInvitationLink, initialTeamInvitationLinkPermissions);
		return teamInvitationLink;

	}

	// TODO: Remove hasPermission later -> currently just a placeholder, the modifications
	// on raw-database
	// TODO: entries are just allowed with ADMIN Privileges
	@PutMapping("/teaminvitationlink")
	@PreAuthorize("hasPermission(#teamInvitationLink, 'UPDATE_TEAM_INVITATION_LINK') or hasAuthority('ADMIN_TEAM_INVITATION_LINK_PRIVILEGE')")
	public TeamInvitationLink updateTeamInvitationLink(@RequestBody TeamInvitationLink teamInvitationLink) {
		return teamInvitationLinkRepository.save(teamInvitationLink);
	}

	// TODO: Remove hasPermission later -> currently just a placeholder, the modifications
	// on raw-database
	// TODO: entries are just allowed with ADMIN Privileges
	@DeleteMapping("/teaminvitationlink")
	@Transactional
	@PreAuthorize("hasPermission(#teamInvitationLink, 'DELETE_TEAM_INVITATION_LINK') or hasAuthority('ADMIN_TEAM_INVITATION_LINK_PRIVILEGE')")
	public void deleteTeamInvitationLink(@RequestBody TeamInvitationLink teamInvitationLink) {
		teamInvitationLinkRepository.delete(teamInvitationLink);
	}

}