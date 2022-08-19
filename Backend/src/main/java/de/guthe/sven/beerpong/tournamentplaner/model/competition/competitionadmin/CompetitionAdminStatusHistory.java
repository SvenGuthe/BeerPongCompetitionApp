package de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin;

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
	@JoinColumn(name = "competitionadminid", nullable = false)
	@JsonIgnore
	private CompetitionAdmin competitionAdmin;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "competitionadminstatusid", nullable = false)
	@JsonIgnore
	private CompetitionAdminStatus competitionAdminStatus;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	public CompetitionAdminStatusHistory() {
	}

	public CompetitionAdminStatusHistory(Timestamp validFrom, CompetitionAdmin competitionAdmin,
			CompetitionAdminStatus competitionAdminStatus) {
		this.validFrom = validFrom;
		this.competitionAdmin = competitionAdmin;
		this.competitionAdminStatus = competitionAdminStatus;
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
