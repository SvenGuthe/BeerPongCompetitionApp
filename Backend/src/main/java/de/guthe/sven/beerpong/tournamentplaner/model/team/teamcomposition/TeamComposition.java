package de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teamcomposition")
@NamedQuery(name = "TeamComposition.findByTeamIdAndUserId",
		query = "SELECT tc FROM TeamComposition tc WHERE tc.team.id = ?1 AND tc.user.id = ?2")
public class TeamComposition implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teamcompositionid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "teamid", nullable = false)
	@JsonIgnore
	private Team team;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	@JsonIgnore
	private User user;

	@Column(name = "isadmin", nullable = false)
	private Boolean isAdmin;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "teamComposition", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamCompositionStatusHistory> teamCompositionStatusHistories;

	public TeamComposition() {
	}

	public TeamComposition(Team team, User user, Boolean isAdmin) {
		this.team = team;
		this.user = user;
		this.isAdmin = isAdmin;
	}

	public Class<TeamComposition> getACLClass() {
		return TeamComposition.class;
	}

	public Long getId() {
		return id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getAdmin() {
		return isAdmin;
	}

	public void setAdmin(Boolean admin) {
		isAdmin = admin;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public void addTeamCompositionStatus(TeamCompositionStatus teamCompositionStatus) {
		TeamCompositionStatusHistory teamCompositionStatusHistory = new TeamCompositionStatusHistory();
		teamCompositionStatusHistory.setTeamComposition(this);
		teamCompositionStatusHistory.setTeamCompositionStatus(teamCompositionStatus);
		if (this.teamCompositionStatusHistories == null) {
			this.teamCompositionStatusHistories = new ArrayList<>();
		}
		this.teamCompositionStatusHistories.add(teamCompositionStatusHistory);
	}

	public List<TeamCompositionStatusHistory> getTeamCompositionStatusHistories() {
		return teamCompositionStatusHistories;
	}

	public void setTeamCompositionStatusHistories(List<TeamCompositionStatusHistory> teamCompositionStatusHistories) {
		this.teamCompositionStatusHistories = teamCompositionStatusHistories;
	}

}
