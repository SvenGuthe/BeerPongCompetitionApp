package de.guthe.sven.beerpong.tournamentplaner.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionPlayer;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.model.team.TeamComposition;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQuery(name = "Role.findByEmail", query = "SELECT u FROM User u WHERE LOWER(u.email) = LOWER(?1)")
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid", nullable = false)
	private Long id;

	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Column(name = "gamertag", nullable = false)
	private String gamerTag;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamComposition> teamCompositions;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userid", referencedColumnName = "userid"),
			inverseJoinColumns = @JoinColumn(name = "roleid", referencedColumnName = "roleid"))
	@JsonIgnore
	private Collection<Role> roles;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Collection<CompetitionPlayer> competitionPlayers;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "userstatusid")
	private UserStatus userStatus;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Collection<ConfirmationToken> confirmationToken;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private Collection<CompetitionAdmin> competitionAdmins;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getGamerTag() {
		return gamerTag;
	}

	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new LinkedList<>();
		}
		this.roles.add(role);
	}

	public List<TeamComposition> getTeamCompositions() {
		return teamCompositions;
	}

	public void setTeamCompositions(List<TeamComposition> teamCompositions) {
		this.teamCompositions = teamCompositions;
	}

	public void addTeam(Team team) {
		TeamComposition teamComposition = new TeamComposition();
		teamComposition.setUser(this);
		teamComposition.setTeam(team);
		teamComposition.setAdmin(true);
		if (this.teamCompositions == null) {
			this.teamCompositions = new ArrayList<>();
		}
		this.teamCompositions.add(teamComposition);
	}

	public Collection<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

	public Collection<ConfirmationToken> getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(List<ConfirmationToken> confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public void addConfirmationToken(ConfirmationToken confirmationToken) {
		if (this.confirmationToken == null) {
			this.confirmationToken = new ArrayList<>();
		}
		this.confirmationToken.add(confirmationToken);
	}

	public void setCompetitionPlayers(Collection<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

	public void setConfirmationToken(Collection<ConfirmationToken> confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Collection<CompetitionAdmin> getCompetitionAdmins() {
		return competitionAdmins;
	}

	public void setCompetitionAdmins(Collection<CompetitionAdmin> competitionAdmins) {
		this.competitionAdmins = competitionAdmins;
	}
}