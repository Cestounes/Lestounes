package com.lefredy.lestounes.repository;


import com.lefredy.lestounes.schemas.Playlists;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PlaylistsRepository extends MongoRepository<Playlists, UUID> {
}
