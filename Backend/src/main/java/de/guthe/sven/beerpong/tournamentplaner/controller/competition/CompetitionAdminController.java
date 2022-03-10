package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionAdminDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionAdminController {

	private CompetitionAdminRepository competitionAdminRepository;

	@Autowired
	public CompetitionAdminController(CompetitionAdminRepository competitionAdminRepository) {
		this.competitionAdminRepository = competitionAdminRepository;
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

}
