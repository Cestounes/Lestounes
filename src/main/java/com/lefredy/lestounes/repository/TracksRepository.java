package com.lefredy.lestounes.repository;

import com.lefredy.lestounes.schemas.Tracks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TracksRepository extends MongoRepository<Tracks, UUID> {
}
