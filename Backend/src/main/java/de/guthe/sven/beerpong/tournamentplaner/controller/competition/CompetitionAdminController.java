package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionAdminAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionAdminDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionAdminRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionAdminController {

	private final CompetitionAdminRepository competitionAdminRepository;

	private final CompetitionService competitionService;

	@Autowired
	public CompetitionAdminController(CompetitionAdminRepository competitionAdminRepository,
			CompetitionService competitionService) {
		this.competitionAdminRepository = competitionAdminRepository;
		this.competitionService = competitionService;
	}

	@GetMapping("/competitionadmin")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionAdminDTO> getCompetitionAdmins() {
		return competitionAdminRepository.findAll().stream().map(CompetitionAdminDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/competitionadmin/{competitionAdminId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminDTO getCompetitionAdmin(@PathVariable Long competitionAdminId) {
		return new CompetitionAdminDTO(competitionAdminRepository.findById(competitionAdminId).orElseThrow());
	}

	@PostMapping("/competitionadmin")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminDTO addCompetitionAdmin(@RequestBody CompetitionAdminAddDTO competitionAdminAddDTO) {
		return competitionService.addCompetitionAdmin(competitionAdminAddDTO);
	}

}
