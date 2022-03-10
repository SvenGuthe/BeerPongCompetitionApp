package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionStatusRepository;
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
public class CompetitionStatusController {

	private CompetitionStatusRepository competitionStatusRepository;

	@Autowired
	public CompetitionStatusController(CompetitionStatusRepository competitionStatusRepository) {
		this.competitionStatusRepository = competitionStatusRepository;
	}

	@GetMapping("/competitionstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getCompetitionStati(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<CompetitionStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = competitionStatusRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = competitionStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);
	}

	@GetMapping("/competitionstatus/{competitionStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionStatusDTO getCompetitionStatus(@PathVariable Long competitionStatusId) {
		return new CompetitionStatusDTO(competitionStatusRepository.findById(competitionStatusId).orElseThrow());
	}

}
