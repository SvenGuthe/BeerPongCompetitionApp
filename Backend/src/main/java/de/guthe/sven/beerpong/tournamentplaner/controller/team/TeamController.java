package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.team.TeamPermissions;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/team")
public class TeamController {

	private ACLService aclService;

	private TeamRepository teamRepository;

	@Autowired
	public TeamController(ACLService aclService, TeamRepository teamRepository) {
		this.aclService = aclService;
		this.teamRepository = teamRepository;
	}

	@GetMapping("/team")
	@PostFilter("hasPermission(filterObject, 'READ') and hasAuthority('READ_TEAM_PRIVILEGE')")
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@GetMapping("/team/{teamId}")
	@PostAuthorize("hasPermission(returnObject, 'READ') and hasAuthority('READ_TEAM_PRIVILEGE')")
	public Team getTeam(@PathVariable Long teamId) {
		return teamRepository.findById(teamId).get();
	}

	@PostMapping("/team")
	@Transactional
	@PreAuthorize("hasAuthority('WRITE_TEAM_PRIVILEGE')")
	public Team addTeam(@RequestBody Team team) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		Map<Sid, List<Permission>> initialTeamPermissions = TeamPermissions.initialTeamPermissions;
		initialTeamPermissions.put(sidCreator, TeamPermissions.ownerPermissions);

		teamRepository.save(team);
		aclService.setPrivileges(team, initialTeamPermissions);
		return team;
	}

	@PutMapping("/team")
	@PreAuthorize("hasPermission(#team, 'WRITE') and hasAuthority('WRITE_TEAM_PRIVILEGE')")
	public Team updateTeam(@RequestBody Team team) {
		return teamRepository.save(team);
	}

	@DeleteMapping("/team")
	@Transactional
	@PreAuthorize("hasPermission(#team, 'WRITE') and hasAuthority('WRITE_TEAM_PRIVILEGE')")
	public void deleteTeam(@RequestBody Team team) {
		teamRepository.delete(team);
	}

}
