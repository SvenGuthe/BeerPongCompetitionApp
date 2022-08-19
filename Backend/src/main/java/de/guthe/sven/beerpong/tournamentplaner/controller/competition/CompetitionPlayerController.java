package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.CompetitionPlayerAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionPlayerDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionPlayerRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
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
public class CompetitionPlayerController {

	private final ACLService aclService;

	private final CompetitionPlayerRepository competitionPlayerRepository;

	private final CompetitionService competitionService;

	@Autowired
	public CompetitionPlayerController(ACLService aclService, CompetitionPlayerRepository competitionPlayerRepository,
			CompetitionService competitionService) {
		this.aclService = aclService;
		this.competitionPlayerRepository = competitionPlayerRepository;
		this.competitionService = competitionService;
	}

	@GetMapping("/competitionplayer")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionPlayerDTO> getCompetitionPlayers() {
		return competitionPlayerRepository.findAll().stream().map(CompetitionPlayerDTO::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/competitionplayer/{competitionPlayerId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerDTO getCompetitionPlayer(@PathVariable Long competitionPlayerId) {
		return new CompetitionPlayerDTO(competitionPlayerRepository.findById(competitionPlayerId).orElseThrow());
	}

	@PostMapping("/competitionplayer")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerDTO addCompetitionPlayer(@RequestBody CompetitionPlayerAddDTO competitionPlayerAddDTO) {
		return competitionService.addCompetitionPlayer(competitionPlayerAddDTO);
	}

	/*
	 * @PostMapping("/competitionplayer")
	 *
	 * @Transactional
	 *
	 * @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')") public
	 * CompetitionPlayer addCompetitionPlayer(@RequestBody CompetitionPlayer
	 * competitionPlayer) { Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); Sid sidCreator = new
	 * PrincipalSid(authentication);
	 *
	 * Map<Sid, List<Permission>> initialCompetitionPlayerPermissions =
	 * CompetitionPlayerPermissions.initialCompetitionPlayerPermissions;
	 * initialCompetitionPlayerPermissions.put(sidCreator,
	 * CompetitionPlayerPermissions.ownerPermissions);
	 *
	 * competitionPlayerRepository.save(competitionPlayer);
	 * aclService.setPrivileges(competitionPlayer, initialCompetitionPlayerPermissions);
	 * return competitionPlayer; }
	 */

}
