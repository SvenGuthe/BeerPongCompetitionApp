package de.guthe.sven.beerpong.tournamentplaner.dto;

public abstract class ID {

    public Long id;

    public ID(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
