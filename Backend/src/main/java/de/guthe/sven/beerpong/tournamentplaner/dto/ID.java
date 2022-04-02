package de.guthe.sven.beerpong.tournamentplaner.dto;

import javax.validation.constraints.NotNull;

public abstract class ID {

    @NotNull(message = "The id of the DTO have to be set.")
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
