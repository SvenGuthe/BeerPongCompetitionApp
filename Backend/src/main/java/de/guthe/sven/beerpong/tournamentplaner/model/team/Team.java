package de.guthe.sven.beerpong.tournamentplaner.model.team;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teamid")
	private Long id;

	@Column(name = "teamname", nullable = false)
	private String teamName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "isplayerteam", nullable = false)
	private boolean isPlayerTeam;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamInvitationLink> teamInvitationLinks;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamComposition> teamCompositions;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamStatusHistory> teamStatusHistories;

	public Team() {
	}

	public Long getId() {
		return id;
	}

	public Class<Team> getACLClass() {
		return Team.class;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPlayerTeam() {
		return isPlayerTeam;
	}

	public void setPlayerTeam(boolean playerTeam) {
		isPlayerTeam = playerTeam;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<TeamInvitationLink> getTeamInvitationLinks() {
		return teamInvitationLinks;
	}

	public void setTeamInvitationLinks(List<TeamInvitationLink> teamInvitationLinks) {
		this.teamInvitationLinks = teamInvitationLinks;
	}

	public List<TeamComposition> getTeamCompositions() {
		return teamCompositions;
	}

	public void setTeamCompositions(List<TeamComposition> teamCompositions) {
		this.teamCompositions = teamCompositions;
	}

	public List<TeamStatusHistory> getTeamStatusHistories() {
		return teamStatusHistories;
	}

	public void setTeamStatusHistories(List<TeamStatusHistory> teamStatusHistories) {
		this.teamStatusHistories = teamStatusHistories;
	}

	public void addUser(User user) {
		TeamComposition teamComposition = new TeamComposition();
		teamComposition.setTeam(this);
		teamComposition.setUser(user);
		teamComposition.setAdmin(true);
		if (this.teamCompositions == null) {
			this.teamCompositions = new ArrayList<>();
		}
		this.teamCompositions.add(teamComposition);
	}

	public void addTeamInvitationLink(TeamInvitationLink teamInvitationLink) {
		if (this.teamInvitationLinks == null) {
			this.teamInvitationLinks = new ArrayList<>();
		}
		teamInvitationLink.setTeam(this);
		this.teamInvitationLinks.add(teamInvitationLink);
	}

	public void addTeamStatus(TeamStatus teamStatus) {
		TeamStatusHistory teamStatusHistory = new TeamStatusHistory();
		teamStatusHistory.setTeam(this);
		teamStatusHistory.setTeamStatus(teamStatus);
		if (this.teamStatusHistories == null) {
			this.teamStatusHistories = new ArrayList<>();
		}
		this.teamStatusHistories.add(teamStatusHistory);
	}

}
