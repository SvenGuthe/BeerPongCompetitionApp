package de.guthe.sven.beerpong.tournamentplaner.model.team;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "teaminvitationlink")
public class TeamInvitationLink implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teaminvitationlinkid")
	private Long id;

	@Column(name = "teaminvitationlink", nullable = false)
	private String teamInvitationLink;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "teamInvitationLink", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<TeamInvitationLinkHistory> teamInvitationLinkHistories;

	public TeamInvitationLink() {
	}

	public TeamInvitationLink(String teamInvitationLink) {
		this.teamInvitationLink = teamInvitationLink;
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

	public List<TeamInvitationLinkHistory> getTeamInvitationLinkHistories() {
		return teamInvitationLinkHistories;
	}

	public void setTeamInvitationLinkHistories(List<TeamInvitationLinkHistory> teamInvitationLinkHistories) {
		this.teamInvitationLinkHistories = teamInvitationLinkHistories;
	}
}
