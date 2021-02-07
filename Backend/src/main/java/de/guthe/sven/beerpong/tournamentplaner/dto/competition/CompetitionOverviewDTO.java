package de.guthe.sven.beerpong.tournamentplaner.dto.competition;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.CompetitionStatusType;
import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.UserIDAndGamerTagDTO;

import java.sql.Timestamp;

public class CompetitionOverviewDTO {

	private Long id;

	private String competitionName;

	private Timestamp competitionStartTimestamp;

	private Integer minTeams;

	private Integer maxTeams;

	private Double fee;

	private Timestamp registrationStart;

	private Timestamp registrationEnd;

	private String setOfRules;

	private Boolean userIsAdmin;

	private CompetitionStatusType competitionStatusType;

	private UserIDAndGamerTagDTO admin;

	private Integer currentTeams;

	private Boolean registered;

	private Boolean payed;

	public CompetitionOverviewDTO() {
	}

	@Override
	public String toString() {
		return "CompetitionOverviewDTO{" + "id=" + id + ", competitionName='" + competitionName + '\''
				+ ", competitionStartTimestamp=" + competitionStartTimestamp + ", minTeams=" + minTeams + ", maxTeams="
				+ maxTeams + ", fee=" + fee + ", registrationStart=" + registrationStart + ", registrationEnd="
				+ registrationEnd + ", setOfRules='" + setOfRules + '\'' + ", isAdmin=" + userIsAdmin
				+ ", competitionStatusType=" + competitionStatusType + ", admin=" + admin + ", currentTeams="
				+ currentTeams + ", registered=" + registered + ", payed=" + payed + '}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public UserIDAndGamerTagDTO getAdmin() {
		return admin;
	}

	public void setAdmin(UserIDAndGamerTagDTO admin) {
		this.admin = admin;
	}

	public Integer getCurrentTeams() {
		return currentTeams;
	}

	public void setCurrentTeams(Integer currentTeams) {
		this.currentTeams = currentTeams;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}

	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	public void setUserIsAdmin(Boolean admin) {
		userIsAdmin = admin;
	}

	public Boolean getUserIsAdmin() {
		return userIsAdmin;
	}

	public CompetitionStatusType getCompetitionStatusType() {
		return competitionStatusType;
	}

	public void setCompetitionStatusType(CompetitionStatusType competitionStatusType) {
		this.competitionStatusType = competitionStatusType;
	}

}
