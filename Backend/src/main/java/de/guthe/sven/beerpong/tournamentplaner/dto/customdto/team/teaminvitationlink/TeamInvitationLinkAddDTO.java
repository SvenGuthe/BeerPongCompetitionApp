package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team.teaminvitationlink;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

import javax.validation.constraints.NotNull;

// ID = Team ID
public class TeamInvitationLinkAddDTO extends ID {

	@NotNull(message = "teamInvitationLink in TeamInvitationLinkAddDTO have to be set.")
	String teamInvitationLink;

	public TeamInvitationLinkAddDTO(Long id, String teamInvitationLink) {
		super(id);
		this.teamInvitationLink = teamInvitationLink;
	}

	public String getTeamInvitationLink() {
		return teamInvitationLink;
	}

	public void setTeamInvitationLink(String teamInvitationLink) {
		this.teamInvitationLink = teamInvitationLink;
	}

	@Override
	public String toString() {
		return "TeamInvitationLinkAddDTO{" + "teamInvitationLink='" + teamInvitationLink + '\'' + ", id=" + id + '}';
	}

}
