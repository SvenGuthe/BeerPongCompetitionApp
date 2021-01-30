package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamInvitationLinkRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

	public List<TeamInvitationLink> getAllTeamInvitationLinks() {
		return teamInvitationLinkRepository.findAll();
	}

	public TeamInvitationLink getTeamInvitationLink(@PathVariable Long teamInvitationLinkId) {
		return teamInvitationLinkRepository.findById(teamInvitationLinkId).get();
	}

}
