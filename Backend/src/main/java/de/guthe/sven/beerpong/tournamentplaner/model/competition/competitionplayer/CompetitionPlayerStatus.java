package de.guthe.sven.beerpong.tournamentplaner.model.competition.competitionplayer;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionPlayerStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competitionplayerstatus")
public class CompetitionPlayerStatus implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "competitionplayerstatusid")
	private Long id;

	@Column(name = "competitionplayerstatusdescription", nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private CompetitionPlayerStatusType competitionPlayerStatusDescription;

	@Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "competitionPlayerStatus", fetch = FetchType.LAZY,
			cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	private List<CompetitionPlayer> competitionPlayers;

	public CompetitionPlayerStatus(CompetitionPlayerStatusType competitionPlayerStatusDescription) {
		this.competitionPlayerStatusDescription = competitionPlayerStatusDescription;
	}

	public CompetitionPlayerStatus() {

	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return CompetitionPlayerStatus.class;
	}

	public CompetitionPlayerStatusType getCompetitionPlayerStatusDescription() {
		return competitionPlayerStatusDescription;
	}

	public void setCompetitionPlayerStatusDescription(CompetitionPlayerStatusType competitionPlayerStatusDescription) {
		this.competitionPlayerStatusDescription = competitionPlayerStatusDescription;
	}

	public List<CompetitionPlayer> getCompetitionPlayers() {
		return competitionPlayers;
	}

	public void setCompetitionPlayers(List<CompetitionPlayer> competitionPlayers) {
		this.competitionPlayers = competitionPlayers;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public void addCompetitionPlayer(CompetitionPlayer competitionPlayer) {
		if (this.competitionPlayers == null) {
			this.competitionPlayers = new ArrayList<>();
		}
		competitionPlayer.setCompetitionPlayerStatus(this);
		this.competitionPlayers.add(competitionPlayer);
	}

}
