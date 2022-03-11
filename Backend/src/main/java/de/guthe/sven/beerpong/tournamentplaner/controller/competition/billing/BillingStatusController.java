package de.guthe.sven.beerpong.tournamentplaner.controller.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.dto.PaginationDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition.BillingStatusDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/competition/billing")
public class BillingStatusController {

	private BillingStatusRepository billingStatusRepository;

	@Autowired
	public BillingStatusController(BillingStatusRepository billingStatusRepository) {
		this.billingStatusRepository = billingStatusRepository;
	}

	@GetMapping("/billingstatus")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public PaginationDTO<EnumDTO> getBillingStati(@RequestParam int page, @RequestParam int size, @RequestParam String search) {

		Page<BillingStatus> pageRequest;
		if (search.equals("")) {
			pageRequest = billingStatusRepository.findAll(PageRequest.of(page, size));
		} else {
			pageRequest = billingStatusRepository.findAll(search, PageRequest.of(page, size));
		}

		List<EnumDTO> data = pageRequest.stream().map(EnumDTO::new).collect(Collectors.toList());

		return new PaginationDTO<>(
				pageRequest.getTotalElements(),
				pageRequest.getTotalPages(),
				data
		);

	}

	@GetMapping("/billingstatus/{billingStatusId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public BillingStatusDTO getBillingStatus(@PathVariable Long billingStatusId) {
		return new BillingStatusDTO(billingStatusRepository.findById(billingStatusId).orElseThrow());
	}

}
