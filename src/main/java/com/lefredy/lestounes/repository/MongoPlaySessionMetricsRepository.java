package com.lefredy.lestounes.repository;

import com.lefredy.lestounes.schemas.PlaySessionMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MongoPlaySessionMetricsRepository extends MongoRepository<PlaySessionMetrics, UUID> {
    List<PlaySessionMetrics> findByUserId(UUID userId);
}