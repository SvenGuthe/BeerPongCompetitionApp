package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authentication.User;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "competitionplayer")
public class CompetitionPlayer implements ACLObjectInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "competitionplayerid")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "competitionteamid")
    @JsonIgnore
    private CompetitionTeam competitionTeam;

    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnore
    private User user;

    @Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
    private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

    public CompetitionPlayer() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Class getACLClass() {
        return CompetitionPlayer.class;
    }

    public CompetitionTeam getCompetitionTeam() {
        return competitionTeam;
    }

    public void setCompetitionTeam(CompetitionTeam competitionTeam) {
        this.competitionTeam = competitionTeam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
