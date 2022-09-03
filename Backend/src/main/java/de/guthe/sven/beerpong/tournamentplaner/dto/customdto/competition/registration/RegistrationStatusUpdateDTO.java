package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

public class RegistrationStatusUpdateDTO extends ID {

	@NotNull(message = "registrationStatusType in RegistrationStatusUpdateDTO have to be set.")
	private RegistrationStatusType registrationStatusType;

	public RegistrationStatusUpdateDTO(Long id, RegistrationStatusType registrationStatusType) {
		super(id);
		this.registrationStatusType = registrationStatusType;
	}

	public RegistrationStatusType getRegistrationStatusType() {
		return registrationStatusType;
	}

	public void setRegistrationStatusType(RegistrationStatusType registrationStatusType) {
		this.registrationStatusType = registrationStatusType;
	}

	@Override
	public String toString() {
		return "RegistrationStatusUpdateDTO{" + "registrationStatusType=" + registrationStatusType + ", id=" + id + '}';
	}

}
