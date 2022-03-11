package de.guthe.sven.beerpong.tournamentplaner.controller.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.competition.CompetitionPlayerPermissions;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.CompetitionPlayerDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayer;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.CompetitionPlayerRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition")
public class CompetitionPlayerController {

	private ACLService aclService;

	private CompetitionPlayerRepository competitionPlayerRepository;

	@Autowired
	public CompetitionPlayerController(ACLService aclService, CompetitionPlayerRepository competitionPlayerRepository) {
		this.aclService = aclService;
		this.competitionPlayerRepository = competitionPlayerRepository;
	}

	@GetMapping("/competitionplayer")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionPlayerDTO> getCompetitionPlayers() {
		return competitionPlayerRepository.findAll().stream().map(CompetitionPlayerDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/competitionplayer/{competitionPlayerId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerDTO getCompetitionPlayer(@PathVariable Long competitionPlayerId) {
		return new CompetitionPlayerDTO(competitionPlayerRepository.findById(competitionPlayerId).orElseThrow());
	}

	@PostMapping("/competitionplayer")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayer addCompetitionPlayer(@RequestBody CompetitionPlayer competitionPlayer) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Sid sidCreator = new PrincipalSid(authentication);

		Map<Sid, List<Permission>> initialCompetitionPlayerPermissions = CompetitionPlayerPermissions.initialCompetitionPlayerPermissions;
		initialCompetitionPlayerPermissions.put(sidCreator, CompetitionPlayerPermissions.ownerPermissions);

		competitionPlayerRepository.save(competitionPlayer);
		aclService.setPrivileges(competitionPlayer, initialCompetitionPlayerPermissions);
		return competitionPlayer;
	}

}
