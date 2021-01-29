package de.guthe.sven.beerpong.tournamentplaner.model.team;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
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

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<TeamInvitationLink> teamInvitationLinks;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<TeamComposition> teamCompositions;

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

	public TeamComposition addUser(User user) {
		TeamComposition teamComposition = new TeamComposition();
		teamComposition.setTeam(this);
		teamComposition.setUser(user);
		teamComposition.setAdmin(true);
		return teamComposition;
	}

}
