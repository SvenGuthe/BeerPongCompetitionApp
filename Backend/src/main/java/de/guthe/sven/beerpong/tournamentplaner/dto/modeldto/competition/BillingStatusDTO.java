package de.guthe.sven.beerpong.tournamentplaner.dto.modeldto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.EnumDTO;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatusHistory;

import java.sql.Timestamp;

public class BillingStatusDTO extends EnumDTO {

    private Long billingStatusId;

    private BillingStatusType billingStatusDescription;

    private Timestamp creationTime;

    private Timestamp validFrom;

    private Timestamp validTo;

    public BillingStatusDTO(Long id, BillingStatusType billingStatusDescription, Timestamp creationTime, Timestamp validFrom, Timestamp validTo) {
        super(id, billingStatusDescription.name());
        this.billingStatusId = id;
        this.billingStatusDescription = billingStatusDescription;
        this.creationTime = creationTime;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public BillingStatusDTO(BillingStatus billingStatus, Timestamp validFrom, Timestamp validTo) {
        super(billingStatus.getId(), billingStatus.getBillingStatusDescription().name());
        this.billingStatusId = billingStatus.getId();
        this.billingStatusDescription = billingStatus.getBillingStatusDescription();
        this.creationTime = billingStatus.getCreationTime();
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public BillingStatusDTO(BillingStatus billingStatus) {
        super(billingStatus.getId(), billingStatus.getBillingStatusDescription().name());
        this.billingStatusId = billingStatus.getId();
        this.billingStatusDescription = billingStatus.getBillingStatusDescription();
        this.creationTime = billingStatus.getCreationTime();
        this.validFrom = null;
        this.validTo = null;
    }

    public BillingStatusDTO(BillingStatusHistory billingStatusHistory) {
        super(billingStatusHistory.getId(), billingStatusHistory.getBillingStatus().getBillingStatusDescription().name());
        this.billingStatusId = billingStatusHistory.getBillingStatus().getId();
        this.billingStatusDescription = billingStatusHistory.getBillingStatus().getBillingStatusDescription();
        this.creationTime = billingStatusHistory.getBillingStatus().getCreationTime();
        this.validFrom = billingStatusHistory.getValidFrom();
        this.validTo = billingStatusHistory.getValidTo();
    }

    public BillingStatusType getBillingStatusDescription() {
        return billingStatusDescription;
    }

    public void setBillingStatusDescription(BillingStatusType billingStatusDescription) {
        this.billingStatusDescription = billingStatusDescription;
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

    public Long getBillingStatusId() {
        return billingStatusId;
    }

    public void setBillingStatusId(Long billingStatusId) {
        this.billingStatusId = billingStatusId;
    }
}
