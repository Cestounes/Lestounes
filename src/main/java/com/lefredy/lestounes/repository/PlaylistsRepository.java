package com.lefredy.lestounes.repository;


import com.lefredy.lestounes.schemas.Playlists;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface PlaylistsRepository extends MongoRepository<Playlists, UUID> {
    List<Playlists> id(UUID id);
}
