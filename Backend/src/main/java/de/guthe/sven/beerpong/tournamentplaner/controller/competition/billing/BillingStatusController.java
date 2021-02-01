package de.guthe.sven.beerpong.tournamentplaner.controller.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.billing.BillingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition/billing")
public class BillingStatusController {

    private BillingStatusRepository billingStatusRepository;

    @Autowired
    public BillingStatusController(BillingStatusRepository billingStatusRepository) {
        this.billingStatusRepository = billingStatusRepository;
    }

    @GetMapping("/billingstatus")
    @PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public List<BillingStatus> getBillingStati() {
        return billingStatusRepository.findAll();
    }

    @GetMapping("/billingstatus/{billingStatusId}")
    @PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public BillingStatus getBillingStatus(@PathVariable Long billingStatusId) {
        return billingStatusRepository.findById(billingStatusId).orElseThrow();
    }

    @PostMapping("/billingstatus")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public BillingStatus addBillingStatus(@RequestBody BillingStatus billingStatus) {
        return billingStatusRepository.save(billingStatus);
    }

    @PutMapping("/billingstatus")
    @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public BillingStatus updateBillingStatus(@RequestBody BillingStatus billingStatus) {
        return billingStatusRepository.save(billingStatus);
    }

    @DeleteMapping("/billingstatus")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public void deleteBillingStatus(@RequestBody BillingStatus billingStatus) {
        billingStatusRepository.delete(billingStatus);
    }

}
