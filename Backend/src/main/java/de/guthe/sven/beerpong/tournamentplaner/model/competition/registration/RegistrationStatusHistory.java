package de.guthe.sven.beerpong.tournamentplaner.model.competition.registration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "registrationstatushistory")
public class RegistrationStatusHistory implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "registrationstatushistoryid")
	private Long id;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp")
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionteamid")
	@JsonIgnore
	private CompetitionTeam competitionTeam;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "registrationstatusid")
	@JsonIgnore
	private RegistrationStatus registrationStatus;

	public RegistrationStatusHistory() {
	}

	public RegistrationStatusHistory(Timestamp validFrom, CompetitionTeam competitionTeam, RegistrationStatus registrationStatus) {
		this.validFrom = validFrom;
		this.competitionTeam = competitionTeam;
		this.registrationStatus = registrationStatus;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return RegistrationStatusHistory.class;
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

	public CompetitionTeam getCompetitionTeam() {
		return competitionTeam;
	}

	public void setCompetitionTeam(CompetitionTeam competitionTeam) {
		this.competitionTeam = competitionTeam;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

}
