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

	@Column(name = "competitionstatusdescription", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private CompetitionStatusType competitionStatusType;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	public CompetitionStatus() {
	}

	public CompetitionStatus(CompetitionStatusType competitionStatusType) {
		this.competitionStatusType = competitionStatusType;
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

	@Override
	public String toString() {
		return "CompetitionStatus{" + "id=" + id + ", competitionStatusType=" + competitionStatusType
				+ ", creationTime=" + creationTime + '}';
	}

}
