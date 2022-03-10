package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.team.TeamPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.team.TeamMetaDataDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.team.TeamDetailDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
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

	private TeamStatusRepository teamStatusRepository;

	@Autowired
	public TeamController(ACLService aclService, TeamRepository teamRepository, TeamStatusRepository teamStatusRepository) {
		this.aclService = aclService;
		this.teamRepository = teamRepository;
		this.teamStatusRepository = teamStatusRepository;
	}

	@GetMapping("/team")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamDetailDTO> getTeams() {
		Collection<Team> teams = teamRepository.findAll();
		return teams.stream().map(TeamDetailDTO::new).collect(Collectors.toList());
	}

	@PutMapping("/team")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamDetailDTO> updateTeamMetaData(@RequestBody TeamMetaDataDTO teamMetaDataDTO) {
		Optional<Team> effectedTeamOption = teamRepository.findById(teamMetaDataDTO.getId());

		if (effectedTeamOption.isPresent()) {
			Team effectedTeam = effectedTeamOption.get();
			effectedTeam.setTeamName(teamMetaDataDTO.getTeamName());

			List<TeamStatusHistory> teamStatusHistories = effectedTeam.getTeamStatusHistories();
			List<TeamStatusHistory> newTeamStatusHistories = teamStatusHistories.stream().peek(teamStatusHistory -> {
				if (teamStatusHistory.getValidTo() == null) {
					teamStatusHistory.setValidTo(new Timestamp(new Date().getTime()));
				}
			}).collect(Collectors.toList());

			TeamStatusHistory newStatusHistory = new TeamStatusHistory();
			newStatusHistory.setTeam(effectedTeam);

			List<TeamStatus> checkIfStatusAvailable = teamStatusRepository.findByDescription(teamMetaDataDTO.getCurrentTeamStatusType());

			TeamStatus newStatus = new TeamStatus();

			if (checkIfStatusAvailable.size() == 0) {
				newStatus.setTeamStatusDescription(teamMetaDataDTO.getCurrentTeamStatusType());
			} else {
				newStatus = checkIfStatusAvailable.get(0);
			}

			newStatusHistory.setTeamStatus(newStatus);

			newTeamStatusHistories.add(newStatusHistory);

			effectedTeam.setTeamStatusHistories(newTeamStatusHistories);

			teamRepository.save(effectedTeam);

			List<TeamDetailDTO> teamDetailDTOS = new LinkedList<>();
			teamDetailDTOS.add(new TeamDetailDTO(effectedTeam));

			return teamDetailDTOS;

		} else {
			throw new NotFoundException("No Team found with id " + teamMetaDataDTO.getId());
		}
	}

	@GetMapping("/team/{teamId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamDetailDTO getTeam(@PathVariable Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow();
		return new TeamDetailDTO(team);
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
