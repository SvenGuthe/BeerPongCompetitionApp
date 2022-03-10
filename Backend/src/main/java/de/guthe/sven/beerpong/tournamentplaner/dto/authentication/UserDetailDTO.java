package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import de.guthe.sven.beerpong.tournamentplaner.model.authentication.Privilege;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.UserStatus;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailDTO {

    static class ConfirmationToken {

        private Long id;

        private String confirmationToken;

        private Timestamp createdDate;

        public ConfirmationToken(Long id, String confirmationToken, Timestamp createdDate) {
            this.id = id;
            this.confirmationToken = confirmationToken;
            this.createdDate = createdDate;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getConfirmationToken() {
            return confirmationToken;
        }

        public void setConfirmationToken(String confirmationToken) {
            this.confirmationToken = confirmationToken;
        }

        public Timestamp getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Timestamp createdDate) {
            this.createdDate = createdDate;
        }
    }

    static class Role {

        private Long id;

        private String name;

        Collection<Privilege> privileges;

        public Role(Long id, String name, Collection<Privilege> privileges) {
            this.id = id;
            this.name = name;
            this.privileges = privileges;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Collection<Privilege> getPrivileges() {
            return privileges;
        }

        public void setPrivileges(Collection<Privilege> privileges) {
            this.privileges = privileges;
        }
    }

    static class Team {

        private Long id;

        private String teamName;

        private boolean isPlayerTeam;

        private Timestamp creationTime;

        private Boolean isAdmin;

        public Team(Long id, String teamName, boolean isPlayerTeam, Timestamp creationTime, Boolean isAdmin) {
            this.id = id;
            this.teamName = teamName;
            this.isPlayerTeam = isPlayerTeam;
            this.creationTime = creationTime;
            this.isAdmin = isAdmin;
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

        public Boolean getAdmin() {
            return isAdmin;
        }

        public void setAdmin(Boolean admin) {
            isAdmin = admin;
        }
    }

    private Long id;

    private String firstName;

    private String lastName;

    private String gamerTag;

    private String email;

    private boolean enabled;

    private Timestamp creationTime;

    private UserStatus userStatus;

    private Collection<Role> roles;

    private Collection<Team> teams;

    private Collection<ConfirmationToken> confirmationToken;

    public UserDetailDTO() {

    }

    public UserDetailDTO(User user) {
        List<Role> roles = user.getRoles().stream().map(role -> {
            Collection<Privilege> privileges = role.getPrivileges();
            Long roleId = role.getId();
            String name = role.getName();

            return new Role (
                    roleId,
                    name,
                    privileges
            );
        }).collect(Collectors.toList());

        List<Team> teams = user.getTeamCompositions().stream().map(teamComposition -> {
            Long id = teamComposition.getTeam().getId();
            String teamName = teamComposition.getTeam().getTeamName();
            boolean isPlayerTeam = teamComposition.getTeam().isPlayerTeam();
            Timestamp creationTime = teamComposition.getTeam().getCreationTime();
            Boolean isAdmin = teamComposition.getAdmin();
            return new Team(
                    id,
                    teamName,
                    isPlayerTeam,
                    creationTime,
                    isAdmin
            );
        }).collect(Collectors.toList());

        List<ConfirmationToken> confirmationTokens = user.getConfirmationToken().stream().map(confirmationToken -> {
            Long id = confirmationToken.getId();
            String confirmationTokenString = confirmationToken.getConfirmationToken();
            Timestamp createdDate = confirmationToken.getCreatedDate();
            return new ConfirmationToken(
                    id,
                    confirmationTokenString,
                    createdDate
            );
        }).collect(Collectors.toList());

        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this. gamerTag = user.getGamerTag();
        this.email = user.getEmail();
        this.enabled = user.isEnabled();
        this.creationTime = user.getCreationTime();
        this.userStatus = user.getUserStatus();
        this.roles = roles;
        this.teams = teams;
        this.confirmationToken = confirmationTokens;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Collection<ConfirmationToken> getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(Collection<ConfirmationToken> confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Collection<Team> getTeams() {
        return teams;
    }

    public void setTeams(Collection<Team> teams) {
        this.teams = teams;
    }
}
