package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamCompositionDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamCompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
	public List<TeamCompositionDTO> getTeamCompositions() {
		return teamCompositionRepository.findAll().stream().map(TeamCompositionDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/teamcomposition/{teamCompositionId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamCompositionDTO getTeamComposition(@PathVariable Long teamCompositionId) {
		return new TeamCompositionDTO(teamCompositionRepository.findById(teamCompositionId).orElseThrow());
	}

}
