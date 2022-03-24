package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamCompositionAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamCompositionUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamCompositionDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamCompositionRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamCompositionController {

	private TeamCompositionRepository teamCompositionRepository;

	private TeamService teamService;

	@Autowired
	public TeamCompositionController(TeamCompositionRepository teamCompositionRepository,
									 TeamService teamService) {
		this.teamCompositionRepository = teamCompositionRepository;
		this.teamService = teamService;
	}

	@GetMapping("/teamcomposition")
	@PostFilter("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamCompositionDTO> getTeamCompositions() {
		return teamCompositionRepository.findAll().stream().map(TeamCompositionDTO::new).collect(Collectors.toList());
	}

	@PutMapping("/teamcomposition")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamCompositionDTO updateTeamComposition(@RequestBody TeamCompositionUpdateDTO teamCompositionUpdateDTO) {
		return teamService.updateTeamComposition(teamCompositionUpdateDTO);
	}

	@PostMapping("/teamcomposition")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamCompositionDTO addTeamComposition(@RequestBody TeamCompositionAddDTO teamCompositionAddDTO) {
		return teamService.addTeamComposition(teamCompositionAddDTO);
	}

	@GetMapping("/teamcomposition/{teamCompositionId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamCompositionDTO getTeamComposition(@PathVariable Long teamCompositionId) {
		return new TeamCompositionDTO(teamCompositionRepository.findById(teamCompositionId).orElseThrow());
	}

}
