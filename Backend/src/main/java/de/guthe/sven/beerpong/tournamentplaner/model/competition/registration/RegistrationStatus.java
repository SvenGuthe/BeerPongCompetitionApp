package de.guthe.sven.beerpong.tournamentplaner.model.competition.registration;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "registrationstatus")
public class RegistrationStatus implements ACLObjectInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registrationstatusid")
    private Long id;

    @Column(name = "registrationstatusdescription", nullable = false)
    private String registrationStatusDescription;

    @Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
    private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

    public RegistrationStatus() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Class getACLClass() {
        return RegistrationStatus.class;
    }

    public String getRegistrationStatusDescription() {
        return registrationStatusDescription;
    }

    public void setRegistrationStatusDescription(String registrationStatusDescription) {
        this.registrationStatusDescription = registrationStatusDescription;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
