package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.constants.eMediaStartAction;
import com.lefredy.lestounes.constants.eMediaStopAction;
import com.lefredy.lestounes.dtos.playsession.PlaySessionMetricDto;
import com.lefredy.lestounes.repository.MongoPlaySessionMetricsRepository;
import com.lefredy.lestounes.schemas.PlaySessionMetrics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaySessionMetricServiceImplTests {
    @Mock
    private MongoPlaySessionMetricsRepository playSessionMetricsRepository;
    @InjectMocks
    private PlaySessionMetricServiceImpl playSessionMetricService;

    private PlaySessionMetricDto createDto() {
        return new PlaySessionMetricDto(UUID.randomUUID(), "Titre", UUID.randomUUID(), 0L, 1000L,
                eMediaStartAction.START, eMediaStopAction.END, new Date());
    }

    @Test
    void getUsersMetrics_returnAllMetrics() {
        List<PlaySessionMetrics> metrics = List.of(new PlaySessionMetrics(createDto()), new PlaySessionMetrics(createDto()));
        when(playSessionMetricsRepository.findAll()).thenReturn(metrics);
        assertEquals(metrics, playSessionMetricService.getUsersMetrics());
        verify(playSessionMetricsRepository).findAll();
    }

    @Test
    void getThisUserMetric_returnUserMetrics() {
        UUID userId = UUID.randomUUID();
        List<PlaySessionMetrics> metrics = List.of(new PlaySessionMetrics(createDto()));
        when(playSessionMetricsRepository.findByUserId(userId)).thenReturn(metrics);
        assertEquals(metrics, playSessionMetricService.getThisUserMetric(userId));
        verify(playSessionMetricsRepository).findByUserId(userId);
    }

    @Test
    void createPlaySessionMetric_returnSavedMetric() {
        PlaySessionMetricDto dto = createDto();
        when(playSessionMetricsRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PlaySessionMetrics saved = playSessionMetricService.createPlaySessionMetric(dto);

        assertEquals(dto.trackId(), saved.getTrackId());
        assertEquals("Titre", saved.getTrackTitle());
        verify(playSessionMetricsRepository).save(any());
    }
}