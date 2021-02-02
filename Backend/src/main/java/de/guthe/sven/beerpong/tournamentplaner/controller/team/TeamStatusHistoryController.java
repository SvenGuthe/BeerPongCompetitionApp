package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamStatusHistoryController {

	private TeamStatusHistoryRepository teamStatusHistoryRepository;

	@Autowired
	public TeamStatusHistoryController(TeamStatusHistoryRepository teamStatusHistoryRepository) {
		this.teamStatusHistoryRepository = teamStatusHistoryRepository;
	}

	@GetMapping("/teamstatushistory")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamStatusHistory> getTeamStatusHistories() {
		return teamStatusHistoryRepository.findAll();
	}

	@GetMapping("/teamstatushistory/{teamStatusHistoryId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamStatusHistory getTeamStatusHistory(@PathVariable Long teamStatusHistoryId) {
		return teamStatusHistoryRepository.findById(teamStatusHistoryId).orElseThrow();
	}

	@PostMapping("/teamstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamStatusHistory addTeamStatusHistory(@RequestBody TeamStatusHistory teamStatusHistory) {
		return teamStatusHistoryRepository.save(teamStatusHistory);
	}

	@PutMapping("/teamstatushistory")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamStatusHistory updateTeamStatusHistory(@RequestBody TeamStatusHistory teamStatusHistory) {
		return teamStatusHistoryRepository.save(teamStatusHistory);
	}

	@DeleteMapping("/teamstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public void deleteTeamStatusHistory(@RequestBody TeamStatusHistory teamStatusHistory) {
		teamStatusHistoryRepository.delete(teamStatusHistory);
	}

}
