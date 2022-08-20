package de.guthe.sven.beerpong.tournamentplaner.model.team;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamComposition;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition.TeamCompositionStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teaminvitationlink.TeamInvitationLink;
import de.guthe.sven.beerpong.tournamentplaner.model.team.teaminvitationlink.TeamInvitationLinkHistory;

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

	@Column(name = "teamname", nullable = false, unique = true)
	private String teamName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "isplayerteam", nullable = false)
	private boolean isPlayerTeam;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamInvitationLinkHistory> teamInvitationLinkHistories;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamComposition> teamCompositions;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamStatusHistory> teamStatusHistories;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JsonIgnore
	private List<CompetitionTeam> competitionTeams;

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

	public List<TeamInvitationLinkHistory> getTeamInvitationLinkHistories() {
		return teamInvitationLinkHistories;
	}

	public void setTeamInvitationLinkHistories(List<TeamInvitationLinkHistory> teamInvitationLinkHistories) {
		this.teamInvitationLinkHistories = teamInvitationLinkHistories;
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

	public void addUser(User user, Boolean admin, TeamCompositionStatus teamCompositionStatus) {
		TeamComposition teamComposition = new TeamComposition();
		teamComposition.setTeam(this);
		teamComposition.setUser(user);
		teamComposition.setAdmin(admin);
		teamComposition.addTeamCompositionStatus(teamCompositionStatus);
		if (this.teamCompositions == null) {
			this.teamCompositions = new ArrayList<>();
		}
		this.teamCompositions.add(teamComposition);
	}

	public void addTeamInvitationLink(TeamInvitationLink teamInvitationLink) {
		TeamInvitationLinkHistory teamInvitationLinkHistory = new TeamInvitationLinkHistory();
		teamInvitationLinkHistory.setTeam(this);
		teamInvitationLinkHistory.setTeamInvitationLink(teamInvitationLink);
		if (this.teamInvitationLinkHistories == null) {
			this.teamInvitationLinkHistories = new ArrayList<>();
		}
		this.teamInvitationLinkHistories.add(teamInvitationLinkHistory);
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

	public List<CompetitionTeam> getCompetitionTeams() {
		return competitionTeams;
	}

	public void setCompetitionTeams(List<CompetitionTeam> competitionTeams) {
		this.competitionTeams = competitionTeams;
	}

}
