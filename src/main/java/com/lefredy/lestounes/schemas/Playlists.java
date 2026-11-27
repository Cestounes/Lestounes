package com.lefredy.lestounes.schemas;

import com.lefredy.lestounes.dtos.user.PlaylistsDto;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document
public class Playlists {
    private UUID id;
    private User user;
    @Indexed(unique = true)
    private String name;
    private List<UUID> tracks;
    private Date updatedAt;
    private Boolean deleted;

    public Playlists(PlaylistsDto playlistsDto) {
        this.id = UUID.randomUUID();
        this.name = playlistsDto.getName();
        this.tracks = playlistsDto.getTracks();
        this.updatedAt = new Date();
        this.deleted = false;
    }

    public Playlists() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UUID> getTracks() {
        return tracks;
    }

    public void setTracks(List<UUID> tracks) {
        this.tracks = tracks;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}


