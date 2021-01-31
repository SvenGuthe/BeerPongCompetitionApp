package de.guthe.sven.beerpong.tournamentplaner.model.competition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;
import de.guthe.sven.beerpong.tournamentplaner.model.team.Team;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "competitionteam")
public class CompetitionTeam implements ACLObjectInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "competitionteamid")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teamid")
    @JsonIgnore
    private Team team;

    @ManyToOne
    @JoinColumn(name = "competitionid")
    @JsonIgnore
    private Competition competition;

    @Column(name = "competitionteamname", nullable = false)
    private String competitionTeamName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "creationtime", columnDefinition = "timestamp default current_timestamp")
    private Timestamp creationTime = new Timestamp(System.currentTimeMillis());

    @OneToMany(mappedBy = "competitionTeam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CompetitionPlayer> competitionPlayers;

    public CompetitionTeam() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Class getACLClass() {
        return CompetitionTeam.class;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public String getCompetitionTeamName() {
        return competitionTeamName;
    }

    public void setCompetitionTeamName(String competitionTeamName) {
        this.competitionTeamName = competitionTeamName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
