package com.lefredy.lestounes.dtos.user;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlaylistsUpdateDto {

    @Indexed(unique = true)
    private Optional<String> name = Optional.empty();
    private Optional<List<UUID>> tracks = Optional.empty();

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<List<UUID>> getTracks() {
        return tracks;
    }

    public void setTracks(Optional<List<UUID>> tracks) {
        this.tracks = tracks;
    }
}
