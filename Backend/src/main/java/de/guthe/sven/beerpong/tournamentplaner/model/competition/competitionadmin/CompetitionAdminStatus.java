package de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionAdminStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "competitionadminstatus")
public class CompetitionAdminStatus implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionadminstatusid")
	private Long id;

	@Column(name = "competitionadminstatusdescription", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private CompetitionAdminStatusType competitionAdminStatusDescription;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "competitionAdminStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionAdminStatusHistory> competitionAdminStatusHistories;

	public CompetitionAdminStatus() {
	}

	public CompetitionAdminStatus(CompetitionAdminStatusType competitionAdminStatusDescription) {
		this.competitionAdminStatusDescription = competitionAdminStatusDescription;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionAdminStatus.class;
	}

	public CompetitionAdminStatusType getCompetitionAdminStatusDescription() {
		return competitionAdminStatusDescription;
	}

	public void setCompetitionAdminStatusDescription(CompetitionAdminStatusType competitionAdminStatusDescription) {
		this.competitionAdminStatusDescription = competitionAdminStatusDescription;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<CompetitionAdminStatusHistory> getCompetitionAdminStatusHistories() {
		return competitionAdminStatusHistories;
	}

	public void setCompetitionAdminStatusHistories(
			List<CompetitionAdminStatusHistory> competitionAdminStatusHistories) {
		this.competitionAdminStatusHistories = competitionAdminStatusHistories;
	}

	@Override
	public String toString() {
		return "CompetitionAdminStatus{" + "id=" + id + ", competitionAdminStatusDescription="
				+ competitionAdminStatusDescription + ", creationTime=" + creationTime
				+ ", competitionAdminStatusHistories=" + competitionAdminStatusHistories + '}';
	}

}
