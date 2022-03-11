package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionPlayerStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayerStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionPlayerStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionPlayerStatusController {

	private CompetitionPlayerStatusRepository competitionPlayerStatusRepository;

	@Autowired
	public CompetitionPlayerStatusController(CompetitionPlayerStatusRepository competitionPlayerStatusRepository) {
		this.competitionPlayerStatusRepository = competitionPlayerStatusRepository;
	}

	@GetMapping("/competitionplayerstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getCompetitionPlayerStati(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<CompetitionPlayerStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = competitionPlayerStatusRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = competitionPlayerStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

	}

	@GetMapping("/competitionplayerstatus/{competitionPlayerStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerStatusDTO getCompetitionPlayerStatus(@PathVariable Long competitionPlayerStatusId) {
		return new CompetitionPlayerStatusDTO(competitionPlayerStatusRepository.findById(competitionPlayerStatusId).orElseThrow());
	}


}
