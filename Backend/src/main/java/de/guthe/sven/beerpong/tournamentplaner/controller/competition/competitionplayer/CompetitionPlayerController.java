package de.guthe.sven.beerpong.tournamentplaner.controller.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionplayer.CompetitionPlayerAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionplayer.CompetitionPlayerDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionplayer.CompetitionPlayerRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.ACLService;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition/competitionplayer")
public class CompetitionPlayerController {

	private final ACLService aclService;

	private final CompetitionPlayerRepository competitionPlayerRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param aclService service to set privileges for objects
	 * @param competitionPlayerRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition player
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public CompetitionPlayerController(ACLService aclService, CompetitionPlayerRepository competitionPlayerRepository,
			CompetitionService competitionService) {
		this.aclService = aclService;
		this.competitionPlayerRepository = competitionPlayerRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(CompetitionPlayerController.class);

	/**
	 * Route to return all Competition Player TODO: Change this (if we need the route) to
	 * a paginated result
	 * @return a list of Data Transfer Object with the Competition Player information
	 */
	@GetMapping("/competitionplayer")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionPlayerDTO> getCompetitionPlayers() {
		logger.info("Fetch all Competition Player");
		return competitionPlayerRepository.findAll().stream().map(CompetitionPlayerDTO::new)
				.collect(Collectors.toList());
	}

	/**
	 * Route to get a single Competition Player by the ID
	 * @param competitionPlayerId the id of the Competition Player in the database
	 * @return the Data Transfer Object with the Competition Player Information
	 */
	@GetMapping("/competitionplayer/{competitionPlayerId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerDTO getCompetitionPlayer(@PathVariable Long competitionPlayerId) {
		logger.info("Trying to find a Competition Player with id: " + competitionPlayerId);

		Optional<CompetitionPlayer> competitionPlayer = competitionPlayerRepository.findById(competitionPlayerId);

		if (competitionPlayer.isEmpty()) {
			throw new RuntimeException("Competition Player not present with given id " + competitionPlayerId);
		}
		else {
			return new CompetitionPlayerDTO(competitionPlayer.get());
		}
	}

	/**
	 * Route to store a new Competition Player
	 * @param competitionPlayerAddDTO a Data Transfer Object with information to create a
	 * new Competition Player
	 * @return the Data Transfer Object with the stored Competition Player Information
	 */
	@PostMapping("/competitionplayer")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionPlayerDTO addCompetitionPlayer(@RequestBody CompetitionPlayerAddDTO competitionPlayerAddDTO) {
		logger.info("Trying to store: " + competitionPlayerAddDTO);
		return competitionService.addCompetitionPlayer(competitionPlayerAddDTO);
	}

	// TODO: Edit the comment here (IDK for know if we need permissions to the object)
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
