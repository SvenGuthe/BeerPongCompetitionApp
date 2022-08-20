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
	private List<CompetitionPlayerStatusHistory> competitionPlayerStatusHistories;

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

	public List<CompetitionPlayerStatusHistory> getCompetitionPlayers() {
		return competitionPlayerStatusHistories;
	}

	public void setCompetitionPlayers(List<CompetitionPlayerStatusHistory> competitionPlayerStatusHistories) {
		this.competitionPlayerStatusHistories = competitionPlayerStatusHistories;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

}
