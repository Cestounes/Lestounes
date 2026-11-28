package com.lefredy.lestounes.service;

import com.lefredy.lestounes.dtos.playsession.PlaySessionMetricDto;
import com.lefredy.lestounes.schemas.PlaySessionMetrics;

import java.util.List;
import java.util.UUID;

public interface PlaySessionMetricService {
    List<PlaySessionMetrics> getUsersMetrics();

    List<PlaySessionMetrics> getThisUserMetric(UUID userId);

    PlaySessionMetrics createPlaySessionMetric(PlaySessionMetricDto playSessionMetricDto);
}