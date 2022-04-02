package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.BillingStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

public class BillingStatusUpdateDTO extends ID {

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
