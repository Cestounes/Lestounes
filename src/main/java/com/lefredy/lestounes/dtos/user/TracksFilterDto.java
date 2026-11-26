package com.lefredy.lestounes.dtos.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class TracksFilterDto {
    @Size(min=3,max=100)
    @NotNull
    @NotBlank
    String genre;
    @Size(min=3,max=150)
    @NotNull
    @NotBlank
    String title;
    @Size(min=3, max=100)
    @NotNull
    @NotBlank
    String album;
    @Size(min=3, max=200)
    @NotNull
    @NotBlank
    String artists;
    @Size(min=3, max=200)
    @NotNull
    @NotBlank
    String albumArtist;
    Integer page = 0;
    @Max(1000)
    Integer pageNbLimit = 100;
    Date updatedAt;

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artists = artists;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageNbLimit(Integer pageNbLimit) {
        this.pageNbLimit = pageNbLimit;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getArtist() {
        return artists;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public int getPage() {
        return page;
    }

    public int getPageNbLimit() {
        return pageNbLimit;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
