package com.lefredy.lestounes.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.List;

public class TracksDto {
    @NotBlank
    String path;
    @NotNull
    @NotBlank
    String title;
    @NotNull
    @PositiveOrZero
    Long length;
    List<String> artists;
    @NotNull
    @NotBlank
    String album;
    @NotNull
    @NotBlank
    String albumArtist;
    @NotNull
    @NotBlank
    String composer;
    @NotNull
    @NotBlank
    Date date;
    @NotNull
    @NotBlank
    List<String> genres;
    @NotNull
    @PositiveOrZero
    Integer trackNumber;
    @NotNull
    @PositiveOrZero
    Integer discNumber;
    @NotNull
    String comment;
    @NotNull
    Date updatedAt;
    @NotNull
    Boolean deleted;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber (Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
