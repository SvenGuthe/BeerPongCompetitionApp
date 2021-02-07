package de.guthe.sven.beerpong.tournamentplaner.dto.competition;

import de.guthe.sven.beerpong.tournamentplaner.dto.authentication.UserInformationDTO;

import java.util.Set;

public class CompetitionListDTO {

	private UserInformationDTO user;

	private Set<CompetitionOverviewDTO> upcomingCompetitions;

	private Set<CompetitionOverviewDTO> closedCompetitions;

	private Set<CompetitionOverviewDTO> ownCompetitions;

	public CompetitionListDTO() {
	}

	public UserInformationDTO getUser() {
		return user;
	}

	public void setUser(UserInformationDTO user) {
		this.user = user;
	}

	public Set<CompetitionOverviewDTO> getUpcomingCompetitions() {
		return upcomingCompetitions;
	}

	public void setUpcomingCompetitions(Set<CompetitionOverviewDTO> upcomingCompetitions) {
		this.upcomingCompetitions = upcomingCompetitions;
	}

	public Set<CompetitionOverviewDTO> getClosedCompetitions() {
		return closedCompetitions;
	}

	public void setClosedCompetitions(Set<CompetitionOverviewDTO> closedCompetitions) {
		this.closedCompetitions = closedCompetitions;
	}

	public Set<CompetitionOverviewDTO> getOwnCompetitions() {
		return ownCompetitions;
	}

	public void setOwnCompetitions(Set<CompetitionOverviewDTO> ownCompetitions) {
		this.ownCompetitions = ownCompetitions;
	}

	@Override
	public String toString() {
		return "CompetitionListDTO{" + "user=" + user + ", upcomingCompetitions=" + upcomingCompetitions
				+ ", closedCompetitions=" + closedCompetitions + ", ownCompetitions=" + ownCompetitions + '}';
	}

}
