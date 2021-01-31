package de.guthe.sven.beerpong.tournamentplaner.model.team;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "teaminvitationlink")
public class TeamInvitationLink implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teaminvitationlinkid")
	private Long id;

	@Column(name = "teaminvitationlink", nullable = false)
	private String teamInvitationLink;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	;

	@ManyToOne
	@JoinColumn(name = "teamid")
	private Team team;

	public TeamInvitationLink() {
	}

	public Class<TeamInvitationLink> getACLClass() {
		return TeamInvitationLink.class;
	}

	public Long getId() {
		return id;
	}

	public String getTeamInvitationLink() {
		return teamInvitationLink;
	}

	public void setTeamInvitationLink(String teamInvitationLink) {
		this.teamInvitationLink = teamInvitationLink;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}
