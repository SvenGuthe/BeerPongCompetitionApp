package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionAdminStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdminStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionAdminStatusRepository;
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
public class CompetitionAdminStatusController {

	private CompetitionAdminStatusRepository competitionAdminStatusRepository;

	@Autowired
	public CompetitionAdminStatusController(CompetitionAdminStatusRepository competitionAdminStatusRepository) {
		this.competitionAdminStatusRepository = competitionAdminStatusRepository;
	}

	@GetMapping("/competitionadminstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getCompetitionAdminStati(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<CompetitionAdminStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = competitionAdminStatusRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = competitionAdminStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

	}

	@GetMapping("/competitionadminstatus/{competitionAdminStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminStatusDTO getCompetitionAdminStatus(@PathVariable Long competitionAdminStatusId) {
		return new CompetitionAdminStatusDTO(competitionAdminStatusRepository.findById(competitionAdminStatusId).orElseThrow());
	}

}
