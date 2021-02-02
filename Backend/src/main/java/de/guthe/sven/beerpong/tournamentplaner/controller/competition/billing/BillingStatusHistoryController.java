package de.guthe.sven.beerpong.tournamentplaner.controller.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition/billing")
public class BillingStatusHistoryController {

	private BillingStatusHistoryRepository billingStatusHistoryRepository;

	@Autowired
	public BillingStatusHistoryController(BillingStatusHistoryRepository billingStatusHistoryRepository) {
		this.billingStatusHistoryRepository = billingStatusHistoryRepository;
	}

	@GetMapping("/billingstatushistory")
	@PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public List<BillingStatusHistory> getBillingStatusHistories() {
		return billingStatusHistoryRepository.findAll();
	}

	@GetMapping("/billingstatushistory/{billingStatusHistoryId}")
	@PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public BillingStatusHistory getBillingStatusHistory(@PathVariable Long billingStatusHistoryId) {
		return billingStatusHistoryRepository.findById(billingStatusHistoryId).orElseThrow();
	}

	@PostMapping("/billingstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public BillingStatusHistory addBillingStatusHistory(@RequestBody BillingStatusHistory billingStatusHistory) {
		return billingStatusHistoryRepository.save(billingStatusHistory);
	}

	@PutMapping("/billingstatushistory")
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public BillingStatusHistory updateBillingStatusHistory(@RequestBody BillingStatusHistory billingStatusHistory) {
		return billingStatusHistoryRepository.save(billingStatusHistory);
	}

	@DeleteMapping("/billingstatushistory")
	@Transactional
	@PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
	public void deleteBillingStatusHistory(@RequestBody BillingStatusHistory billingStatusHistory) {
		billingStatusHistoryRepository.delete(billingStatusHistory);
	}

}
