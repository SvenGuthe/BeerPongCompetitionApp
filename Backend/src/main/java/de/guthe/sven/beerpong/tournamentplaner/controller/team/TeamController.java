package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.team.TeamPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.team.TeamOverviewDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamController {

	private ACLService aclService;

	private TeamRepository teamRepository;

	private TeamService teamService;

	@Autowired
	public TeamController(ACLService aclService, TeamRepository teamRepository, TeamService teamService) {
		this.aclService = aclService;
		this.teamRepository = teamRepository;
		this.teamService = teamService;
	}

	@GetMapping("/team")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<Team> getTeams() {
		return teamRepository.findAll();
	}

	@GetMapping("/team/{teamId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public Team getTeam(@PathVariable Long teamId) {
		return teamRepository.findById(teamId).orElseThrow();
	}

	@PostMapping("/team")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public Team addTeam(@RequestBody Team team) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		Map<Sid, List<Permission>> initialTeamPermissions = TeamPermissions.initialTeamPermissions;
		initialTeamPermissions.put(sidCreator, TeamPermissions.ownerPermissions);

		teamRepository.save(team);
		aclService.setPrivileges(team, initialTeamPermissions);
		return team;
	}

	// TODO: Remove hasPermission later -> currently just a placeholder, the modifications
	// on raw-database
	// TODO: entries are just allowed with ADMIN Privileges
	@PutMapping("/team")
	@PreAuthorize("hasPermission(#team, 'UPDATE_TEAM') or hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public Team updateTeam(@RequestBody Team team) {
		return teamRepository.save(team);
	}

	// TODO: Remove hasPermission later -> currently just a placeholder, the modifications
	// on raw-database
	// TODO: entries are just allowed with ADMIN Privileges
	@DeleteMapping("/team")
	@Transactional
	@PreAuthorize("hasPermission(#team, 'DELETE_TEAM') or hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public void deleteTeam(@RequestBody Team team) {
		teamRepository.delete(team);
	}

	@GetMapping("/team/overview")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamOverviewDTO> getActiveTeamsOverview() {
		return teamService.getActiveTeams(0, 10);
	}

}
