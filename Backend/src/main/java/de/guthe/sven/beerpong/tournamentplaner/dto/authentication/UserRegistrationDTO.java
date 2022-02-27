package de.guthe.sven.beerpong.tournamentplaner.dto.authentication;

import javax.persistence.Column;

public class UserRegistrationDTO {

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "gamertag", nullable = false)
    private String gamerTag;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public UserRegistrationDTO(String firstName, String lastName, String gamerTag, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gamerTag = gamerTag;
        this.email = email;
        this.password = password;
    }

    public UserRegistrationDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
