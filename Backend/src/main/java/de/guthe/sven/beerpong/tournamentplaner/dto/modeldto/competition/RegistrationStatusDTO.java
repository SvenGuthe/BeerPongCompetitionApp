package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.RegistrationStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;
import de.guthe.sven.beerpong.tournamentplaner.dto.customdto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;

import java.sql.Timestamp;

public class RegistrationStatusDTO extends EnumDTO {

    private Long registrationStatusId;

    private RegistrationStatusType registrationStatusDescription;

    private Timestamp creationTime;

    private Timestamp validFrom;

    private Timestamp validTo;

    public RegistrationStatusDTO(Long id, RegistrationStatusType registrationStatusDescription, Timestamp creationTime, Timestamp validFrom, Timestamp validTo) {
        super(id, registrationStatusDescription.name());
        this.registrationStatusId = id;
        this.registrationStatusDescription = registrationStatusDescription;
        this.creationTime = creationTime;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public RegistrationStatusDTO(RegistrationStatus registrationStatus, Timestamp validFrom, Timestamp validTo) {
        super(registrationStatus.getId(), registrationStatus.getRegistrationStatusDescription().name());
        this.registrationStatusId = registrationStatus.getId();
        this.registrationStatusDescription = registrationStatus.getRegistrationStatusDescription();
        this.creationTime = registrationStatus.getCreationTime();
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public RegistrationStatusDTO(RegistrationStatus registrationStatus) {
        super(registrationStatus.getId(), registrationStatus.getRegistrationStatusDescription().name());
        this.registrationStatusId = registrationStatus.getId();
        this.registrationStatusDescription = registrationStatus.getRegistrationStatusDescription();
        this.creationTime = registrationStatus.getCreationTime();
        this.validFrom = null;
        this.validTo = null;
    }

    public RegistrationStatusDTO(RegistrationStatusHistory registrationStatusHistory) {
        super(registrationStatusHistory.getId(), registrationStatusHistory.getRegistrationStatus().getRegistrationStatusDescription().name());
        this.registrationStatusId = registrationStatusHistory.getRegistrationStatus().getId();
        this.registrationStatusDescription = registrationStatusHistory.getRegistrationStatus().getRegistrationStatusDescription();
        this.creationTime = registrationStatusHistory.getRegistrationStatus().getCreationTime();
        this.validFrom = registrationStatusHistory.getValidFrom();
        this.validTo = registrationStatusHistory.getValidTo();
    }

    public RegistrationStatusType getRegistrationStatusDescription() {
        return registrationStatusDescription;
    }

    public void setRegistrationStatusDescription(RegistrationStatusType registrationStatusDescription) {
        this.registrationStatusDescription = registrationStatusDescription;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Timestamp validFrom) {
        this.validFrom = validFrom;
    }

    public Timestamp getValidTo() {
        return validTo;
    }

    public void setValidTo(Timestamp validTo) {
        this.validTo = validTo;
    }

    public Long getRegistrationStatusId() {
        return registrationStatusId;
    }

    public void setRegistrationStatusId(Long registrationStatusId) {
        this.registrationStatusId = registrationStatusId;
    }
}
