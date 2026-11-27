package com.lefredy.lestounes.repository;

import com.lefredy.lestounes.schemas.PlaySessionMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoPlaySessionMetricsRepository extends MongoRepository<PlaySessionMetrics, UUID> {
}