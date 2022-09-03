package de.guthe.sven.beerpong.tournamentplaner.model.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NamedQuery(name = "TeamStatus.findByDescription",
		query = "SELECT ts FROM TeamStatus ts WHERE teamStatusDescription = ?1")
@Table(name = "teamstatus")
public class TeamStatus implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teamstatusid")
	private Long id;

	@Column(name = "teamstatusdescription", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private TeamStatusType teamStatusDescription;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "teamStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamStatusHistory> teamStatusHistories;

	public TeamStatus() {
	}

	public TeamStatus(TeamStatusType teamStatusDescription) {
		this.teamStatusDescription = teamStatusDescription;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return TeamStatus.class;
	}

	public TeamStatusType getTeamStatusDescription() {
		return teamStatusDescription;
	}

	public void setTeamStatusDescription(TeamStatusType teamStatusDescription) {
		this.teamStatusDescription = teamStatusDescription;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<TeamStatusHistory> getTeamStatusHistories() {
		return teamStatusHistories;
	}

	public void setTeamStatusHistories(List<TeamStatusHistory> teamStatusHistoryList) {
		this.teamStatusHistories = teamStatusHistoryList;
	}

	@Override
	public String toString() {
		return "TeamStatus{" + "id=" + id + ", teamStatusDescription=" + teamStatusDescription + ", creationTime="
				+ creationTime + ", teamStatusHistories=" + teamStatusHistories + '}';
	}

}
