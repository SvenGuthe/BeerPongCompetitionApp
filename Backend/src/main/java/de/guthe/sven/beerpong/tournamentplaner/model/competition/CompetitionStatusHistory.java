package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "competitionstatushistory")
public class CompetitionStatusHistory implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionstatushistoryid")
	private Long id;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp")
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionid")
	@JsonIgnore
	private Competition competition;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionstatusid")
	@JsonIgnore
	private CompetitionStatus competitionStatus;

	public CompetitionStatusHistory() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionStatusHistory.class;
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

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public CompetitionStatus getCompetitionStatus() {
		return competitionStatus;
	}

	public void setCompetitionStatus(CompetitionStatus competitionStatus) {
		this.competitionStatus = competitionStatus;
	}

}
