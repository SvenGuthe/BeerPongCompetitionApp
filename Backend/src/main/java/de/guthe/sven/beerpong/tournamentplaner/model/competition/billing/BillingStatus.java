package de.guthe.sven.beerpong.tournamentplaner.model.competition.billing;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "billingstatus")
public class BillingStatus implements ACLObjectInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billingstatusid")
    private Long id;

    @Column(name = "billingstatusdescription", nullable = false)
    private String billingStatusDescription;

    @Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
    private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

    public BillingStatus() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Class getACLClass() {
        return BillingStatus.class;
    }

    public String getBillingStatusDescription() {
        return billingStatusDescription;
    }

    public void setBillingStatusDescription(String billingStatusDescription) {
        this.billingStatusDescription = billingStatusDescription;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

}
