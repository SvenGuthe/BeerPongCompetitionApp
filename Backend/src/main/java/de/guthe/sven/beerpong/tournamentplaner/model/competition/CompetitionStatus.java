package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "competitionstatus")
public class CompetitionStatus implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionstatusid")
	private Long id;

	@Column(name = "competitionstatusdescription", nullable = false)
	@Enumerated(EnumType.STRING)
	private CompetitionStatusType competitionStatusType;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	public CompetitionStatus() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionStatus.class;
	}

	public CompetitionStatusType getCompetitionStatusType() {
		return competitionStatusType;
	}

	public void setCompetitionStatusType(CompetitionStatusType competitionStatusType) {
		this.competitionStatusType = competitionStatusType;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

}
