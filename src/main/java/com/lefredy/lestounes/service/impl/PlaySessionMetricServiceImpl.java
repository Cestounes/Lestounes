package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.dtos.playsession.PlaySessionMetricDto;
import com.lefredy.lestounes.repository.MongoPlaySessionMetricsRepository;
import com.lefredy.lestounes.schemas.PlaySessionMetrics;
import com.lefredy.lestounes.service.PlaySessionMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaySessionMetricServiceImpl implements PlaySessionMetricService {

    @Autowired
    private MongoPlaySessionMetricsRepository playSessionMetricsRepository;

    @Override
    public List<PlaySessionMetrics> getUsersMetrics() {
        return playSessionMetricsRepository.findAll();
    }

    @Override
    public List<PlaySessionMetrics> getThisUserMetric(UUID userId) {
        return playSessionMetricsRepository.findByUserId(userId);
    }

    @Override
    public PlaySessionMetrics createPlaySessionMetric(PlaySessionMetricDto playSessionMetricDto) {
        return playSessionMetricsRepository.save(new PlaySessionMetrics(playSessionMetricDto));
    }
}