package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.TeamStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team")
public class TeamStatusController {

	private final TeamStatusRepository teamStatusRepository;

	private final TeamService teamService;

	@Autowired
	public TeamStatusController(TeamStatusRepository teamStatusRepository, TeamService teamService) {
		this.teamStatusRepository = teamStatusRepository;
		this.teamService = teamService;
	}

	@GetMapping("/teamstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getTeamStati(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		Page<TeamStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = teamStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			pageRequest = teamStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	@PostMapping("/teamstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public List<TeamStatusDTO> updateTeamStatus(@RequestBody TeamStatusUpdateDTO teamStatusUpdateDTO) {
		return teamService.updateTeamStatus(teamStatusUpdateDTO);
	}

	@GetMapping("/teamstatus/{teamStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamStatusDTO getTeamStatus(@PathVariable Long teamStatusId) {
		return new TeamStatusDTO(teamStatusRepository.findById(teamStatusId).orElseThrow());
	}

}
