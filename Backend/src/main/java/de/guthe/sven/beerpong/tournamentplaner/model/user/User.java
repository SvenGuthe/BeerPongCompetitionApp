package de.guthe.sven.beerpong.tournamentplaner.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.user.confirmationtoken.ConfirmationTokenHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin.CompetitionAdmin;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamComposition;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

	@Column(name = "gamertag", nullable = false, unique = true)
	private String gamerTag;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamComposition> teamCompositions;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private Collection<UserRole> userRoles;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private Collection<CompetitionPlayer> competitionPlayers;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<UserStatusHistory> userStatusHistories;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<ConfirmationTokenHistory> confirmationTokenHistories;

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

	public List<UserStatusHistory> getUserStatusHistories() {
		return userStatusHistories;
	}

	public void setUserStatusHistories(List<UserStatusHistory> userStatusHistories) {
		this.userStatusHistories = userStatusHistories;
	}

	public void addUserStatusHistory(UserStatusHistory userStatusHistories) {
		if (this.userStatusHistories == null) {
			this.userStatusHistories = new LinkedList<>();
		}
		this.userStatusHistories.add(userStatusHistories);
	}

	public Collection<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Collection<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void addUserRole(UserRole userRole) {
		if (this.userRoles == null) {
			this.userRoles = new LinkedList<>();
		}
		this.userRoles.add(userRole);
	}

	public void setRoles(Collection<Role> roles) {
		Collection<UserRole> userRoles = roles.stream().map(role -> {
			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUser(this);
			return userRole;
		}).collect(Collectors.toList());
		setUserRoles(userRoles);
	}

	public List<TeamComposition> getTeamCompositions() {
		return teamCompositions;
	}

	public void setTeamCompositions(List<TeamComposition> teamCompositions) {
		this.teamCompositions = teamCompositions;
	}

	public void addTeam(Team team, Boolean admin, TeamCompositionStatus teamCompositionStatus) {
		TeamComposition teamComposition = new TeamComposition();
		teamComposition.setUser(this);
		teamComposition.setTeam(team);
		teamComposition.setAdmin(admin);
		teamComposition.addTeamCompositionStatus(teamCompositionStatus);
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

	public List<ConfirmationTokenHistory> getConfirmationTokenHistories() {
		return confirmationTokenHistories;
	}

	public void setConfirmationTokenHistories(List<ConfirmationTokenHistory> confirmationTokenHistories) {
		this.confirmationTokenHistories = confirmationTokenHistories;
	}

	public void addConfirmationTokenHistory(ConfirmationTokenHistory confirmationTokenHistory) {
		if (this.confirmationTokenHistories == null) {
			this.confirmationTokenHistories = new LinkedList<>();
		}
		this.confirmationTokenHistories.add(confirmationTokenHistory);
	}

	public void setCompetitionPlayers(Collection<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

	public Collection<CompetitionAdmin> getCompetitionAdmins() {
		return competitionAdmins;
	}

	public void setCompetitionAdmins(Collection<CompetitionAdmin> competitionAdmins) {
		this.competitionAdmins = competitionAdmins;
	}

}