package com.lefredy.lestounes.dtos.user;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.UUID;

public class PlaylistsDto {

    @Indexed(unique = true)
    private String name;
    private List<UUID> tracks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getTracks() {
        return tracks;
    }

    public void setTracks(List<UUID> tracks) {
        this.tracks = tracks;
    }
}
