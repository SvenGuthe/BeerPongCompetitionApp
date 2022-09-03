package de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamCompositionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NamedQuery(name = "TeamCompositionStatus.findByDescription",
		query = "SELECT tcs FROM TeamCompositionStatus tcs WHERE teamCompositionStatusDescription = ?1")
@Table(name = "teamcompositionstatus")
public class TeamCompositionStatus implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teamcompositionstatusid")
	private Long id;

	@Column(name = "teamcompositionstatusdescription", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private TeamCompositionStatusType teamCompositionStatusType;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "teamCompositionStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamCompositionStatusHistory> teamCompositionStatusHistories;

	public TeamCompositionStatus() {
	}

	public TeamCompositionStatus(TeamCompositionStatusType teamCompositionStatusType) {
		this.teamCompositionStatusType = teamCompositionStatusType;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return TeamCompositionStatus.class;
	}

	public TeamCompositionStatusType getTeamCompositionStatusType() {
		return teamCompositionStatusType;
	}

	public void setTeamCompositionStatusType(TeamCompositionStatusType teamCompositionStatusType) {
		this.teamCompositionStatusType = teamCompositionStatusType;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<TeamCompositionStatusHistory> getTeamCompositionStatusHistories() {
		return teamCompositionStatusHistories;
	}

	public void setTeamCompositionStatusHistories(List<TeamCompositionStatusHistory> teamCompositionStatusHistories) {
		this.teamCompositionStatusHistories = teamCompositionStatusHistories;
	}

	@Override
	public String toString() {
		return "TeamCompositionStatus{" + "id=" + id + ", teamCompositionStatusType=" + teamCompositionStatusType
				+ ", creationTime=" + creationTime + ", teamCompositionStatusHistories="
				+ teamCompositionStatusHistories + '}';
	}

}
