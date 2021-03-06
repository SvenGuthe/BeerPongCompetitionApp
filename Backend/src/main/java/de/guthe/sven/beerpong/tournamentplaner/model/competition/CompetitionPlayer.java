package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "competitionplayer")
public class CompetitionPlayer implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionplayerid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionteamid")
	@JsonIgnore
	private CompetitionTeam competitionTeam;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	@JsonIgnore
	private User user;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionplayerstatusid")
	@JsonIgnore
	private CompetitionPlayerStatus competitionPlayerStatus;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	public CompetitionPlayer() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionPlayer.class;
	}

	public CompetitionTeam getCompetitionTeam() {
		return competitionTeam;
	}

	public void setCompetitionTeam(CompetitionTeam competitionTeam) {
		this.competitionTeam = competitionTeam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CompetitionPlayerStatus getCompetitionPlayerStatus() {
		return competitionPlayerStatus;
	}

	public void setCompetitionPlayerStatus(CompetitionPlayerStatus competitionPlayerStatus) {
		this.competitionPlayerStatus = competitionPlayerStatus;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

}
