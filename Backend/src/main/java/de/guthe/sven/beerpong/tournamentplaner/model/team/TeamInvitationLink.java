package de.guthe.sven.beerpong.tournamentplaner.model.team;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "teaminvitationlink")
public class TeamInvitationLink implements ACLObjectInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "teaminvitationlinkid")
    private Long id;

    @Column(name = "teaminvitationlink", nullable = false)
    private String teamInvitationLink;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationtime", nullable = false)
    private Date creationTime;

    @ManyToOne
    @JoinColumn(name = "teamid")
    private Team team;

    public TeamInvitationLink() {
    }

    public String getACLClassName() {
        return TeamInvitationLink.class.getName();
    }

    public Long getId() {
        return id;
    }

    public String getTeamInvitationLink() {
        return teamInvitationLink;
    }

    public void setTeamInvitationLink(String teamInvitationLink) {
        this.teamInvitationLink = teamInvitationLink;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
