package de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.CompetitionTeam;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competitionplayer")
public class CompetitionPlayer implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionplayerid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionteamid", nullable = false)
	@JsonIgnore
	private CompetitionTeam competitionTeam;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy = "competitionPlayer", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionPlayerStatusHistory> competitionPlayerStatusHistories;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	public CompetitionPlayer() {
	}

	public CompetitionPlayer(CompetitionTeam competitionTeam, User user,
			List<CompetitionPlayerStatusHistory> competitionPlayerStatusHistories) {
		this.competitionTeam = competitionTeam;
		this.user = user;
		this.competitionPlayerStatusHistories = competitionPlayerStatusHistories;
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

	public List<CompetitionPlayerStatusHistory> getCompetitionPlayerStatusHistories() {
		return competitionPlayerStatusHistories;
	}

	public void setCompetitionPlayerStatusHistories(
			List<CompetitionPlayerStatusHistory> competitionPlayerStatusHistories) {
		this.competitionPlayerStatusHistories = competitionPlayerStatusHistories;
	}

	public void addCompetitionPlayerStatus(CompetitionPlayerStatus competitionPlayerStatus) {
		CompetitionPlayerStatusHistory competitionPlayerStatusHistory = new CompetitionPlayerStatusHistory();
		competitionPlayerStatusHistory.setCompetitionPlayer(this);
		competitionPlayerStatusHistory.setCompetitionPlayerStatus(competitionPlayerStatus);
		if (this.competitionPlayerStatusHistories == null) {
			this.competitionPlayerStatusHistories = new ArrayList<>();
		}
		this.competitionPlayerStatusHistories.add(competitionPlayerStatusHistory);
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "CompetitionPlayer{" + "id=" + id + ", competitionTeam=" + competitionTeam + ", user=" + user
				+ ", competitionPlayerStatusHistories=" + competitionPlayerStatusHistories + ", creationTime="
				+ creationTime + '}';
	}

}
