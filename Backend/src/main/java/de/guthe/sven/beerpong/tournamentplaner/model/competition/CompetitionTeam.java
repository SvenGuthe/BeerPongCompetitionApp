package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.billing.BillingStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer.CompetitionPlayer;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatus;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.registration.RegistrationStatusHistory;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competitionteam")
public class CompetitionTeam implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionteamid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "teamid")
	@JsonIgnore
	private Team team;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionid")
	@JsonIgnore
	private Competition competition;

	@Column(name = "competitionteamname", nullable = false)
	private String competitionTeamName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "competitionTeam", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionPlayer> competitionPlayers;

	@OneToMany(mappedBy = "competitionTeam", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<BillingStatusHistory> billingStatusHistories;

	@OneToMany(mappedBy = "competitionTeam", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<RegistrationStatusHistory> registrationStatusHistories;

	public CompetitionTeam() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionTeam.class;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public String getCompetitionTeamName() {
		return competitionTeamName;
	}

	public void setCompetitionTeamName(String competitionTeamName) {
		this.competitionTeamName = competitionTeamName;
	}

	public List<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<BillingStatusHistory> getBillingStatusHistories() {
		return billingStatusHistories;
	}

	public void setBillingStatusHistories(List<BillingStatusHistory> billingStatusHistories) {
		this.billingStatusHistories = billingStatusHistories;
	}

	public List<RegistrationStatusHistory> getRegistrationStatusHistories() {
		return registrationStatusHistories;
	}

	public void setRegistrationStatusHistories(List<RegistrationStatusHistory> registrationStatusHistories) {
		this.registrationStatusHistories = registrationStatusHistories;
	}

	public void addCompetitionPlayer(CompetitionPlayer competitionPlayer) {
		if (this.competitionPlayers == null) {
			this.competitionPlayers = new ArrayList<>();
		}
		competitionPlayer.setCompetitionTeam(this);
		this.competitionPlayers.add(competitionPlayer);
	}

	public void addBillingStatus(BillingStatus billingStatus) {
		BillingStatusHistory billingStatusHistory = new BillingStatusHistory();
		billingStatusHistory.setCompetitionTeam(this);
		billingStatusHistory.setBillingStatus(billingStatus);
		if (this.billingStatusHistories == null) {
			this.billingStatusHistories = new ArrayList<>();
		}
		this.billingStatusHistories.add(billingStatusHistory);
	}

	public void addRegistrationStatus(RegistrationStatus registrationStatus) {
		RegistrationStatusHistory registrationStatusHistory = new RegistrationStatusHistory();
		registrationStatusHistory.setCompetitionTeam(this);
		registrationStatusHistory.setRegistrationStatus(registrationStatus);
		if (this.registrationStatusHistories == null) {
			this.registrationStatusHistories = new ArrayList<>();
		}
		this.registrationStatusHistories.add(registrationStatusHistory);
	}

	@Override
	public String toString() {
		return "CompetitionTeam{" + "id=" + id + ", team=" + team + ", competition=" + competition
				+ ", competitionTeamName='" + competitionTeamName + '\'' + ", password='" + password + '\''
				+ ", creationTime=" + creationTime + ", competitionPlayers=" + competitionPlayers
				+ ", billingStatusHistories=" + billingStatusHistories + ", registrationStatusHistories="
				+ registrationStatusHistories + '}';
	}

}
