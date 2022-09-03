package de.guthe.sven.beerpong.tournamentplaner.controller.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.billing.BillingStatusUpdateDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.BillingStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusRepository;
import de.guthe.sven.beerpong.tournamentplaner.service.competition.CompetitionService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition/billing")
public class BillingStatusController {

	private final BillingStatusRepository billingStatusRepository;

	private final CompetitionService competitionService;

	/**
	 * Constructor to auto-wire the services / components / ...
	 * @param billingStatusRepository jpa repository to handle all database queries
	 * directly in this controller regarding the billing status
	 * @param competitionService service to handle all the transformations / database
	 * queries regarding the competition
	 */
	@Autowired
	public BillingStatusController(BillingStatusRepository billingStatusRepository,
			CompetitionService competitionService) {
		this.billingStatusRepository = billingStatusRepository;
		this.competitionService = competitionService;
	}

	final private Logger logger = LoggerFactory.getLogger(BillingStatusController.class);

	/**
	 * Route to return all Billing Status with Pagination
	 * @param page the number of the page which should be loaded
	 * @param size the size of a single page
	 * @param search an optional search string to filter the results
	 * @return one page of the Billing Status
	 */
	@GetMapping("/billingstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getBillingStatusPage(@RequestParam int page, @RequestParam int size,
			@RequestParam String search) {

		logger.info("Fetch page " + page + " of size " + size + "with filter '" + search + "' of Billing Status");

		Page<BillingStatus> pageRequest;

		// Check if the search parameter is empty (then we do not need to filter the
		// results)
		if (search.equals("")) {
			// SELECT * FROM BillingStatus
			pageRequest = billingStatusRepository.findAll(PageRequest.of(page, size));
		}
		else {
			// SELECT * FROM BillingStatus WHERE Status LIKE search
			pageRequest = billingStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		// Transform the results to the custom Enum Data Transfer Objects
		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		logger.info("Return the following page: " + data);

		// Return the number of all elements, the number of pages with the current size
		// and the data of the current page
		return new PaginationDTO<>(pageRequest.getTotalElements(), pageRequest.getTotalPages(), data);

	}

	/**
	 * Route to get a single Billing Status by the ID
	 * @param billingStatusId the id of the Billing Status in the database
	 * @return the Billing Status
	 */
	@GetMapping("/billingstatus/{billingStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public BillingStatusDTO getBillingStatus(@PathVariable Long billingStatusId) {
		logger.info("Trying to find a Billing Status with id: " + billingStatusId);
		// TODO: check if the result is empty -> If this is the case return a custom
		// error-message
		return new BillingStatusDTO(billingStatusRepository.findById(billingStatusId).orElseThrow());
	}

	/**
	 * Route to update the Billing Status manually
	 * @param billingStatusUpdateDTO the updated entry (id is the identifier)
	 * @return the updated entry if the update was successful
	 */
	@PutMapping("/billingstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public Collection<BillingStatusDTO> updateBillingStatus(
			@RequestBody @NotNull BillingStatusUpdateDTO billingStatusUpdateDTO) {
		logger.info("Trying to update the Billing Status with id = " + billingStatusUpdateDTO.getId()
				+ " and the following content (Wrapped in a DTO): " + billingStatusUpdateDTO);
		return competitionService.updateBillingStatus(billingStatusUpdateDTO);
	}

}
