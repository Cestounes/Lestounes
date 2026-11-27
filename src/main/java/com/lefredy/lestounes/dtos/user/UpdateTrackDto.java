package com.lefredy.lestounes.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Optional;

public class UpdateTrackDto {
    @NotNull
    @NotBlank
    Optional<String> path = Optional.empty();
    @NotNull
    @NotBlank
    Optional<Date> date = Optional.empty();
    @NotNull
    @NotBlank
    Optional<String> comment = Optional.empty();
    @NotNull
    @NotNull
    @NotBlank
    Optional<Boolean> deleted = Optional.empty();

    public void setPath(Optional<String> path) {
        this.path = path;
    }

    public void setDate(Optional<Date> date) {
        this.date = date;
    }

    public void setComment(Optional<String> comment) {
        this.comment = comment;
    }

    public void setDeleted(Optional<Boolean> deleted) {
        this.deleted = deleted;
    }
}
