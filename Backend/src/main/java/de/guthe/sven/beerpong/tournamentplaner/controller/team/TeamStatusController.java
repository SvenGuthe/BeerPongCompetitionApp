package de.guthe.sven.beerpong.tournamentplaner.controller.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.team.TeamStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.team.TeamStatusRepository;
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

	private TeamStatusRepository teamStatusRepository;

	@Autowired
	public TeamStatusController(TeamStatusRepository teamStatusRepository) {
		this.teamStatusRepository = teamStatusRepository;
	}

	@GetMapping("/teamstatus")
	@PreAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getTeamStati(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<TeamStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = teamStatusRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = teamStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

	}

	@GetMapping("/teamstatus/{teamStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_TEAM_PRIVILEGE')")
	public TeamStatusDTO getTeamStatus(@PathVariable Long teamStatusId) {
		return new TeamStatusDTO(teamStatusRepository.findById(teamStatusId).orElseThrow());
	}

}
