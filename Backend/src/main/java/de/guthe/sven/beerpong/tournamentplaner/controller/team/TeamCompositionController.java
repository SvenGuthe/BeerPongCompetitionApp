package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamComposition;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamCompositionController {

	private TeamCompositionRepository teamCompositionRepository;

	@Autowired
	public TeamCompositionController(TeamCompositionRepository teamCompositionRepository) {
		this.teamCompositionRepository = teamCompositionRepository;
	}

	@GetMapping("/teamcomposition")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamComposition> getTeamCompositions() {
		return teamCompositionRepository.findAll();
	}

	@GetMapping("/teamcomposition/{teamCompositionId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamComposition getTeamComposition(@PathVariable Long teamCompositionId) {
		return teamCompositionRepository.findById(teamCompositionId).orElseThrow();
	}

	@PostMapping("/teamcomposition")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamComposition addTeamComposition(@RequestBody TeamComposition teamComposition) {
		return teamCompositionRepository.save(teamComposition);
	}

	@PutMapping("/teamcomposition")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamComposition updateTeamComposition(@RequestBody TeamComposition teamComposition) {
		return teamCompositionRepository.save(teamComposition);
	}

	@DeleteMapping("/teamcomposition")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public void deleteTeamComposition(@RequestBody TeamComposition teamComposition) {
		teamCompositionRepository.delete(teamComposition);
	}

}
