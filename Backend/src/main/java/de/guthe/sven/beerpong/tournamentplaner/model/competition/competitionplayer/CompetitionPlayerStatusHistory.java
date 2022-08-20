package de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "competitionplayerstatushistory")
public class CompetitionPlayerStatusHistory implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionplayerstatushistoryid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionplayerid", nullable = false)
	@JsonIgnore
	private CompetitionPlayer competitionPlayer;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinColumn(name = "competitionplayerstatusid", nullable = false)
	@JsonIgnore
	private CompetitionPlayerStatus competitionPlayerStatus;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	public CompetitionPlayerStatusHistory() {
	}

	public CompetitionPlayerStatusHistory(CompetitionPlayer competitionPlayer,
			CompetitionPlayerStatus competitionPlayerStatus, Timestamp validFrom) {
		this.competitionPlayer = competitionPlayer;
		this.competitionPlayerStatus = competitionPlayerStatus;
		this.validFrom = validFrom;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionPlayerStatusHistory.class;
	}

	public CompetitionPlayer getCompetitionPlayer() {
		return competitionPlayer;
	}

	public void setCompetitionPlayer(CompetitionPlayer competitionPlayer) {
		this.competitionPlayer = competitionPlayer;
	}

	public CompetitionPlayerStatus getCompetitionPlayerStatus() {
		return competitionPlayerStatus;
	}

	public void setCompetitionPlayerStatus(CompetitionPlayerStatus competitionPlayerStatus) {
		this.competitionPlayerStatus = competitionPlayerStatus;
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
