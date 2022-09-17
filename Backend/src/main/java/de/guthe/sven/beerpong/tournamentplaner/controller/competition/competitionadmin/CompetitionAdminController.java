package de.guthe.sven.beerpong.tournamentplaner.controller.competition.competitionadmin;

import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.competitionadmin.CompetitionAdminAddDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.competitionadmin.CompetitionAdminDTO;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.competitionadmin.CompetitionAdminRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition/competitionadmin")
public class CompetitionAdminController {

	private final CompetitionAdminRepository competitionAdminRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param competitionAdminRepository jpa repository to handle all database queries
	 * directly in this controller regarding the competition admin
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public CompetitionAdminController(CompetitionAdminRepository competitionAdminRepository,
			CompetitionService competitionService) {
		this.competitionAdminRepository = competitionAdminRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(CompetitionAdminController.class);

	/**
	 * Route to return all Competition Admins TODO: Change this (if we need the route) to
	 * a paginated result
	 * @return a list of Data Transfer Object with the Competition Admin information
	 */
	@GetMapping("/competitionadmin")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<CompetitionAdminDTO> getCompetitionAdmins() {
		logger.info("Fetch all Competition Admins");
		return competitionAdminRepository.findAll().stream().map(CompetitionAdminDTO::new).collect(Collectors.toList());
	}

	/**
	 * Route to get a single Competition Admin by the ID
	 * @param competitionAdminId the id of the Competition Admin in the database
	 * @return the Data Transfer Object with the Competition Admin Information
	 */
	@GetMapping("/competitionadmin/{competitionAdminId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminDTO getCompetitionAdmin(@PathVariable Long competitionAdminId) {
		logger.info("Trying to find a Competition Admin with id: " + competitionAdminId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new CompetitionAdminDTO(competitionAdminRepository.findById(competitionAdminId).orElseThrow());
	}

	/**
	 * Route to store a new Competition Admin
	 * @param competitionAdminAddDTO a Data Transfer Object with information to create a
	 * new Competition Admin
	 * @return the Data Transfer Object with the stored Competition Admin Information
	 */
	@PostMapping("/competitionadmin")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public CompetitionAdminDTO addCompetitionAdmin(@RequestBody CompetitionAdminAddDTO competitionAdminAddDTO) {
		logger.info("Trying to store: " + competitionAdminAddDTO);
		return competitionService.addCompetitionAdmin(competitionAdminAddDTO);
	}

}
