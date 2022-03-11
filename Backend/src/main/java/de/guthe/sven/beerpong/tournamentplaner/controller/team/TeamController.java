package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.team.TeamPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamDTO;
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

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
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
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamDTO> getTeams() {
		Collection<Team> teams = teamRepository.findAll();
		return teams.stream().map(TeamDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/team/{teamId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamDTO getTeam(@PathVariable Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow();
		return new TeamDTO(team);
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
	// @PutMapping("/team")
	// @PreAuthorize("hasPermission(#team, 'UPDATE_TEAM') or hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	// public Team updateTeam(@RequestBody Team team) {
	// 	return teamRepository.save(team);
	// }

}
