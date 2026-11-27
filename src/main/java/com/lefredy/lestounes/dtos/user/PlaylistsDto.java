package com.lefredy.lestounes.dtos.user;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.UUID;

public class PlaylistsDto {

    @NotNull
    @Indexed(unique = true)
    @NotNull
    private String name;
    @NotNull
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
