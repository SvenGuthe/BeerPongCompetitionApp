package de.guthe.sven.beerpong.tournamentplaner.dto.team;

import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.TeamStatusType;
import de.guthe.sven.beerpong.tournamentplaner.datatype.enums.UserStatusType;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TeamDetailDTO {

    static class TeamStatusHistory {

        private Long id;

        private Timestamp validFrom;

        private Timestamp validTo;

        private TeamStatusType teamStatusDescription;

        public TeamStatusHistory(Long id, Timestamp validFrom, Timestamp validTo, TeamStatusType teamStatusDescription) {
            this.id = id;
            this.validFrom = validFrom;
            this.validTo = validTo;
            this.teamStatusDescription = teamStatusDescription;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public TeamStatusType getTeamStatusDescription() {
            return teamStatusDescription;
        }

        public void setTeamStatusDescription(TeamStatusType teamStatusDescription) {
            this.teamStatusDescription = teamStatusDescription;
        }
    }

    static class User {

        private Long id;

        private String firstName;

        private String lastName;

        private String gamerTag;

        private boolean enabled;

        private boolean isAdmin;

        private Timestamp creationTime;

        private UserStatusType userStatus;

        public User(Long id, String firstName, String lastName, String gamerTag, boolean enabled, boolean isAdmin, Timestamp creationTime, UserStatusType userStatus) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gamerTag = gamerTag;
            this.enabled = enabled;
            this.isAdmin = isAdmin;
            this.creationTime = creationTime;
            this.userStatus = userStatus;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getGamerTag() {
            return gamerTag;
        }

        public void setGamerTag(String gamerTag) {
            this.gamerTag = gamerTag;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public boolean isAdmin() {
            return isAdmin;
        }

        public void setAdmin(boolean admin) {
            isAdmin = admin;
        }

        public Timestamp getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(Timestamp creationTime) {
            this.creationTime = creationTime;
        }

        public UserStatusType getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(UserStatusType userStatus) {
            this.userStatus = userStatus;
        }

    }

    static class TeamInvitationLinkHistory {

        private Long id;

        private Timestamp validFrom;

        private Timestamp validTo;

        private String teamInvitationLink;

        public TeamInvitationLinkHistory(Long id, Timestamp validFrom, Timestamp validTo, String teamInvitationLink) {
            this.id = id;
            this.validFrom = validFrom;
            this.validTo = validTo;
            this.teamInvitationLink = teamInvitationLink;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getTeamInvitationLink() {
            return teamInvitationLink;
        }

        public void setTeamInvitationLink(String teamInvitationLink) {
            this.teamInvitationLink = teamInvitationLink;
        }
    }

    private Long id;

    private String teamName;

    private boolean isPlayerTeam;

    private Timestamp creationTime;

    private Collection<User> members;

    private Collection<TeamStatusHistory> teamStatusHistories;

    private Collection<TeamInvitationLinkHistory> teamInvitationLinkHistories;

    public TeamDetailDTO(Long id, String teamName, boolean isPlayerTeam, Timestamp creationTime, Collection<User> members, Collection<TeamStatusHistory> teamStatusHistories, Collection<TeamInvitationLinkHistory> teamInvitationLinkHistories) {
        this.id = id;
        this.teamName = teamName;
        this.isPlayerTeam = isPlayerTeam;
        this.creationTime = creationTime;
        this.members = members;
        this.teamStatusHistories = teamStatusHistories;
        this.teamInvitationLinkHistories = teamInvitationLinkHistories;
    }

    public TeamDetailDTO(Team team) {
        List<User> members = team.getTeamCompositions().stream().map(teamComposition -> {
                Long userId = teamComposition.getUser().getId();
                String firstName = teamComposition.getUser().getFirstName();
                String lastName= teamComposition.getUser().getLastName();
                String gamerTag = teamComposition.getUser().getGamerTag();
                boolean enabled = teamComposition.getUser().isEnabled();
            boolean isAdmin = teamComposition.getAdmin();
            Timestamp creationTime = teamComposition.getCreationTime();
                UserStatusType userStatus = teamComposition.getUser().getUserStatus().getUserStatus();

            return new TeamDetailDTO.User(
                    userId,
                    firstName,
                    lastName,
                    gamerTag,
                    enabled,
                    isAdmin,
                    creationTime,
                    userStatus
            );
        }).collect(Collectors.toList());

        List<TeamStatusHistory> teamStatusHistories = team.getTeamStatusHistories().stream().map(teamStatusHistory -> {
            Long id = teamStatusHistory.getTeamStatus().getId();
            Timestamp validFrom = teamStatusHistory.getValidFrom();
            Timestamp validTo = teamStatusHistory.getValidTo();
            TeamStatusType teamStatusDescription = teamStatusHistory.getTeamStatus().getTeamStatusDescription();

            return new TeamDetailDTO.TeamStatusHistory(
                    id,
                    validFrom,
                    validTo,
                    teamStatusDescription
            );
        }).collect(Collectors.toList());

        List<TeamInvitationLinkHistory> teamInvitationLinkHistories = team.getTeamInvitationLinkHistories().stream().map(teamInvitationLinkHistory -> {
            Long id = teamInvitationLinkHistory.getTeamInvitationLink().getId();
            Timestamp validFrom = teamInvitationLinkHistory.getValidFrom();
            Timestamp validTo = teamInvitationLinkHistory.getValidTo();
            String teamInvitationLink = teamInvitationLinkHistory.getTeamInvitationLink().getTeamInvitationLink();

            return new TeamDetailDTO.TeamInvitationLinkHistory(
                    id,
                    validFrom,
                    validTo,
                    teamInvitationLink
            );
        }).collect(Collectors.toList());

        this.id = team.getId();
        this.teamName = team.getTeamName();
        this.isPlayerTeam = team.isPlayerTeam();
        this.creationTime = team.getCreationTime();
        this.members = members;
        this.teamStatusHistories = teamStatusHistories;
        this.teamInvitationLinkHistories = teamInvitationLinkHistories;
    }

    public Class<Team> getACLClass() {
        return Team.class;
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

    public boolean isPlayerTeam() {
        return isPlayerTeam;
    }

    public void setPlayerTeam(boolean playerTeam) {
        isPlayerTeam = playerTeam;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Collection<User> getMembers() {
        return members;
    }

    public void setMembers(Collection<User> members) {
        this.members = members;
    }

    public Collection<TeamStatusHistory> getTeamStatusHistories() {
        return teamStatusHistories;
    }

    public void setTeamStatusHistories(Collection<TeamStatusHistory> teamStatusHistories) {
        this.teamStatusHistories = teamStatusHistories;
    }

    public Collection<TeamInvitationLinkHistory> getTeamInvitationLinkHistories() {
        return teamInvitationLinkHistories;
    }

    public void setTeamInvitationLinkHistories(Collection<TeamInvitationLinkHistory> teamInvitationLinkHistories) {
        this.teamInvitationLinkHistories = teamInvitationLinkHistories;
    }
}
