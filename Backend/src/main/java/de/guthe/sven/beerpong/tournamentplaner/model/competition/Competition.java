package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competition")
public class Competition implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionid")
	private Long id;

	@Column(name = "competitionname", nullable = false)
	private String competitionName;

	@Column(name = "competitionstarttimestamp")
	private Timestamp competitionStartTimestamp;

	@Column(name = "minteams")
	private Integer minTeams;

	@Column(name = "maxteams")
	private Integer maxTeams;

	@Column(name = "fee")
	private Double fee;

	@Column(name = "registrationstart")
	private Timestamp registrationStart;

	@Column(name = "registrationend")
	private Timestamp registrationEnd;

	@Column(name = "setofrules")
	private String setOfRules;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "competition", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionTeam> competitionTeams;

	@OneToMany(mappedBy = "competition", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionAdmin> competitionAdmins;

	@OneToMany(mappedBy = "competition", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionStatusHistory> competitionStatusHistories;

	public Competition() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return Competition.class;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public void setCompetitionName(String competitionName) {
		this.competitionName = competitionName;
	}

	public Timestamp getCompetitionStartTimestamp() {
		return competitionStartTimestamp;
	}

	public void setCompetitionStartTimestamp(Timestamp competitionStartTimestamp) {
		this.competitionStartTimestamp = competitionStartTimestamp;
	}

	public Integer getMinTeams() {
		return minTeams;
	}

	public void setMinTeams(Integer minTeams) {
		this.minTeams = minTeams;
	}

	public Integer getMaxTeams() {
		return maxTeams;
	}

	public void setMaxTeams(Integer maxTeams) {
		this.maxTeams = maxTeams;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Timestamp getRegistrationStart() {
		return registrationStart;
	}

	public void setRegistrationStart(Timestamp registrationStart) {
		this.registrationStart = registrationStart;
	}

	public Timestamp getRegistrationEnd() {
		return registrationEnd;
	}

	public void setRegistrationEnd(Timestamp registrationEnd) {
		this.registrationEnd = registrationEnd;
	}

	public String getSetOfRules() {
		return setOfRules;
	}

	public void setSetOfRules(String setOfRules) {
		this.setOfRules = setOfRules;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<CompetitionTeam> getCompetitionTeams() {
		return competitionTeams;
	}

	public void setCompetitionTeams(List<CompetitionTeam> competitionTeams) {
		this.competitionTeams = competitionTeams;
	}

	public List<CompetitionAdmin> getCompetitionAdmins() {
		return competitionAdmins;
	}

	public void setCompetitionAdmins(List<CompetitionAdmin> competitionAdmins) {
		this.competitionAdmins = competitionAdmins;
	}

	public List<CompetitionStatusHistory> getCompetitionStatusHistories() {
		return competitionStatusHistories;
	}

	public void setCompetitionStatusHistories(List<CompetitionStatusHistory> competitionStatusHistories) {
		this.competitionStatusHistories = competitionStatusHistories;
	}

	public void addCompetitionTeam(CompetitionTeam competitionTeam) {
		if (this.competitionTeams == null) {
			this.competitionTeams = new ArrayList<>();
		}
		competitionTeam.setCompetition(this);
		this.competitionTeams.add(competitionTeam);
	}

	public void addCompetitionAdmin(CompetitionAdmin competitionAdmin) {
		if (this.competitionAdmins == null) {
			this.competitionAdmins = new ArrayList<>();
		}
		competitionAdmin.setCompetition(this);
		this.competitionAdmins.add(competitionAdmin);
	}

	public void addCompetitionStatus(CompetitionStatus competitionStatus) {
		CompetitionStatusHistory competitionStatusHistory = new CompetitionStatusHistory();
		competitionStatusHistory.setCompetition(this);
		competitionStatusHistory.setCompetitionStatus(competitionStatus);

		if (this.competitionStatusHistories == null) {
			this.competitionStatusHistories = new ArrayList<>();
		}

		this.competitionStatusHistories.add(competitionStatusHistory);
	}

}
