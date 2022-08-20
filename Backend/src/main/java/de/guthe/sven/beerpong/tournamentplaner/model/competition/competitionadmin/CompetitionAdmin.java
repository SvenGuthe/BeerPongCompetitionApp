package de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.user.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.competition.Competition;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competitionadmin")
public class CompetitionAdmin implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionadminid")
	private Long id;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "competitionid", nullable = false)
	@JsonIgnore
	private Competition competition;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", nullable = false)
	@JsonIgnore
	private User user;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "competitionAdmin", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionAdminStatusHistory> competitionAdminStatusHistories;

	public CompetitionAdmin() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionAdmin.class;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public List<CompetitionAdminStatusHistory> getCompetitionAdminStatusHistories() {
		return competitionAdminStatusHistories;
	}

	public void setCompetitionAdminStatusHistories(
			List<CompetitionAdminStatusHistory> competitionAdminStatusHistories) {
		this.competitionAdminStatusHistories = competitionAdminStatusHistories;
	}

	public void addCompetitionAdminStatus(CompetitionAdminStatus competitionAdminStatus) {
		CompetitionAdminStatusHistory competitionAdminStatusHistory = new CompetitionAdminStatusHistory();
		competitionAdminStatusHistory.setCompetitionAdmin(this);
		competitionAdminStatusHistory.setCompetitionAdminStatus(competitionAdminStatus);
		if (this.competitionAdminStatusHistories == null) {
			this.competitionAdminStatusHistories = new ArrayList<>();
		}
		this.competitionAdminStatusHistories.add(competitionAdminStatusHistory);
	}

}
