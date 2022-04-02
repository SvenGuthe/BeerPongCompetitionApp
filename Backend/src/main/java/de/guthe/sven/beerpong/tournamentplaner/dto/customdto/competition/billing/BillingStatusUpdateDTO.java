package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

public class BillingStatusUpdateDTO extends ID {

    @NotNull(message = "billingStatusType in BillingStatusUpdateDTO have to be set.")
    BillingStatusType billingStatusType;

    public BillingStatusUpdateDTO(Long id, BillingStatusType billingStatusType) {
        super(id);
        this.billingStatusType = billingStatusType;
    }

    public BillingStatusType getBillingStatusType() {
        return billingStatusType;
    }

    public void setBillingStatusType(BillingStatusType billingStatusType) {
        this.billingStatusType = billingStatusType;
    }

}
