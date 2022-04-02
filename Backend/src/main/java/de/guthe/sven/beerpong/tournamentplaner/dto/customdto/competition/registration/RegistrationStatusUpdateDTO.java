package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

public class RegistrationStatusUpdateDTO extends ID {

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

}
