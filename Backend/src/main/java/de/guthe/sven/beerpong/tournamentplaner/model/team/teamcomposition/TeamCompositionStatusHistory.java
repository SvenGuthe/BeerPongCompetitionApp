package de.guthe.sven.beerpong.tournamentplaner.model.team.teamcomposition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "teamcompositionstatushistory")
public class TeamCompositionStatusHistory implements ACLObjectInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "teamcompositionstatushistoryid")
	private Long id;

	@Column(name = "validfrom", columnDefinition = "timestamp default current_timestamp", nullable = false)
	private Timestamp validFrom = new Timestamp(System.currentTimeMillis());

	@Column(name = "validto")
	private Timestamp validTo;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "teamcompositionid", nullable = false)
	@JsonIgnore
	private TeamComposition teamComposition;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "teamcompositionstatusid", nullable = false)
	@JsonIgnore
	private TeamCompositionStatus teamCompositionStatus;

	public TeamCompositionStatusHistory() {
	}

	public TeamCompositionStatusHistory(Timestamp validFrom, TeamComposition teamComposition,
			TeamCompositionStatus teamCompositionStatus) {
		this.validFrom = validFrom;
		this.teamComposition = teamComposition;
		this.teamCompositionStatus = teamCompositionStatus;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class getACLClass() {
		return TeamCompositionStatusHistory.class;
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

	public TeamComposition getTeamComposition() {
		return teamComposition;
	}

	public void setTeamComposition(TeamComposition teamComposition) {
		this.teamComposition = teamComposition;
	}

	public TeamCompositionStatus getTeamCompositionStatus() {
		return teamCompositionStatus;
	}

	public void setTeamCompositionStatus(TeamCompositionStatus teamCompositionStatus) {
		this.teamCompositionStatus = teamCompositionStatus;
	}

	@Override
	public String toString() {
		return "TeamCompositionStatusHistory{" + "id=" + id + ", validFrom=" + validFrom + ", validTo=" + validTo
				+ ", teamComposition=" + teamComposition + ", teamCompositionStatus=" + teamCompositionStatus + '}';
	}

}
