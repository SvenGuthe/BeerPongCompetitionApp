package de.guthe.sven.beerpong.tournamentplaner.model.team;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;

@Entity
@Table(name = "teamcomposition")
@NamedQuery(name = "TeamComposition.findByTeamIdAndUserId",
		query = "SELECT tc FROM TeamComposition tc WHERE tc.team.id = ?1 AND tc.user.userId = ?2")
public class TeamComposition implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teamcompositionid")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "teamid")
	private Team team;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	@Column(name = "isadmin", nullable = false)
	private Boolean isAdmin;

	public TeamComposition() {
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

}
