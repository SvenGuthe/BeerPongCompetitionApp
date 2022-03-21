package de.guthe.sven.beerpong.tournamentplaner.dto.customdto.team;

import de.guthe.sven.beerpong.tournamentplaner.dto.ID;

// ID = Team ID
public class TeamInvitationLinkAddDTO extends ID {

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

}
