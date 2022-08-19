package de.guthe.sven.beerpong.tournamentplaner.model.team.teaminvitationlink;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "teaminvitationlinkhistory")
public class TeamInvitationLinkHistory implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teaminvitationlinkhistoryid")
	private Long id;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "teamid", nullable = false)
	@JsonIgnore
	private Team team;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "teaminvitationlinkid", nullable = false)
	@JsonIgnore
	private TeamInvitationLink teamInvitationLink;

	public TeamInvitationLinkHistory() {
	}

	public TeamInvitationLinkHistory(Team team, TeamInvitationLink teamInvitationLink) {
		this.team = team;
		this.teamInvitationLink = teamInvitationLink;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return TeamInvitationLinkHistory.class;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public TeamInvitationLink getTeamInvitationLink() {
		return teamInvitationLink;
	}

	public void setTeamInvitationLink(TeamInvitationLink teamInvitationLink) {
		this.teamInvitationLink = teamInvitationLink;
	}

}
