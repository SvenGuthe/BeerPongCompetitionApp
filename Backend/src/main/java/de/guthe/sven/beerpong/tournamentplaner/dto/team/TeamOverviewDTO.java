package de.guthe.sven.beerpong.tournamentplaner.dto.team;

public class TeamOverviewDTO {

	private Long id;

	private String teamName;

	public TeamOverviewDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
