package de.guthe.sven.beerpong.tournamentplaner.controller.team.teamcomposition;

import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamCompositionAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamCompositionStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamCompositionUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamcomposition.TeamCompositionDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.teamcomposition.TeamCompositionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionRepository;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.teamcomposition.TeamCompositionStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team/teamcomposition")
public class TeamCompositionController {

	private final TeamCompositionRepository teamCompositionRepository;

	private final TeamCompositionStatusRepository teamCompositionStatusRepository;

	private final TeamService teamService;

	@Autowired
	public TeamCompositionController(TeamCompositionRepository teamCompositionRepository, TeamService teamService,
			TeamCompositionStatusRepository teamCompositionStatusRepository) {
		this.teamCompositionRepository = teamCompositionRepository;
		this.teamService = teamService;
		this.teamCompositionStatusRepository = teamCompositionStatusRepository;
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

	@PutMapping("/teamcompositionstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamCompositionStatusDTO> updateTeamCompositionStatus(
			@RequestBody TeamCompositionStatusUpdateDTO teamCompositionStatusUpdateDTO) {
		return teamService.updateTeamCompositionStatus(teamCompositionStatusUpdateDTO);
	}

	@GetMapping("/teamcompositionstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getTeamCompositionStatus(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {
		Page<TeamCompositionStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = teamCompositionStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			pageRequest = teamCompositionStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

}
