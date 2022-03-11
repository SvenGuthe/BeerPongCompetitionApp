package de.guthe.sven.beerpong.tournamentplaner.model.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

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
	@JoinColumn(name = "teamid")
	@JsonIgnore
	private Team team;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	@JsonIgnore
	private User user;

	@Column(name = "isadmin", nullable = false)
	private Boolean isAdmin;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

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

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

}
