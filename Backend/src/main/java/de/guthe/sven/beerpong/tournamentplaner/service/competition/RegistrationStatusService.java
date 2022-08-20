package de.guthe.sven.beerpong.tournamentplaner.service.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.repository.competition.registration.RegistrationStatusRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationStatusService {

	RegistrationStatusRepository registrationStatusRepository;

	@Autowired
	public RegistrationStatusService(RegistrationStatusRepository registrationStatusRepository) {
		this.registrationStatusRepository = registrationStatusRepository;
	}

	public RegistrationStatus getOrCreateRegistrationStatus(@NotNull RegistrationStatusType registrationStatusType) {
		Optional<RegistrationStatus> registrationStatus = registrationStatusRepository
				.findByStatus(registrationStatusType.name());
		RegistrationStatus finalRegistrationStatus;
		if (registrationStatus.isEmpty()) {
			finalRegistrationStatus = new RegistrationStatus(registrationStatusType);
			registrationStatusRepository.save(finalRegistrationStatus);
		}
		else {
			finalRegistrationStatus = registrationStatus.get();
		}

		return finalRegistrationStatus;
	}

}
