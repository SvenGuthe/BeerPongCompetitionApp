package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "competitionadminstatushistory")
public class CompetitionAdminStatusHistory implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionadminstatushistoryid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionadminid")
	@JsonIgnore
	private CompetitionAdmin competitionAdmin;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionadminstatusid")
	@JsonIgnore
	private CompetitionAdminStatus competitionAdminStatus;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp")
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	public CompetitionAdminStatusHistory() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionAdminStatusHistory.class;
	}

	public CompetitionAdmin getCompetitionAdmin() {
		return competitionAdmin;
	}

	public void setCompetitionAdmin(CompetitionAdmin competitionAdmin) {
		this.competitionAdmin = competitionAdmin;
	}

	public CompetitionAdminStatus getCompetitionAdminStatus() {
		return competitionAdminStatus;
	}

	public void setCompetitionAdminStatus(CompetitionAdminStatus competitionAdminStatus) {
		this.competitionAdminStatus = competitionAdminStatus;
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

}
