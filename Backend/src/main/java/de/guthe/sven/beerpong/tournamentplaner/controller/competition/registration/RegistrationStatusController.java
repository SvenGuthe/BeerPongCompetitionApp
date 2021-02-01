package de.guthe.sven.beerpong.tournamentplaner.controller.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/competition/registration")
public class RegistrationStatusController {

    private RegistrationStatusRepository registrationStatusRepository;

    @Autowired
    public RegistrationStatusController(RegistrationStatusRepository registrationStatusRepository) {
        this.registrationStatusRepository = registrationStatusRepository;
    }

    @GetMapping("/registrationstatus")
    @PostFilter("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public List<RegistrationStatus> getRegistrationStati() {
        return registrationStatusRepository.findAll();
    }

    @GetMapping("/registrationstatus/{registrationStatusId}")
    @PostAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public RegistrationStatus getRegistrationStatus(@PathVariable Long registrationStatusId) {
        return registrationStatusRepository.findById(registrationStatusId).orElseThrow();
    }

    @PostMapping("/registrationstatus")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public RegistrationStatus addRegistrationStatus(@RequestBody RegistrationStatus registrationStatus) {
        return registrationStatusRepository.save(registrationStatus);
    }

    @PutMapping("/registrationstatus")
    @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public RegistrationStatus updateRegistrationStatus(@RequestBody RegistrationStatus registrationStatus) {
        return registrationStatusRepository.save(registrationStatus);
    }

    @DeleteMapping("/registrationstatus")
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN_COMPETITION_PRIVILEGE')")
    public void deleteRegistrationStatus(@RequestBody RegistrationStatus registrationStatus) {
        registrationStatusRepository.delete(registrationStatus);
    }

}
