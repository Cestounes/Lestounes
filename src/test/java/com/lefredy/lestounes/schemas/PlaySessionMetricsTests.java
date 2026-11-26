package com.lefredy.lestounes.schemas;

import com.lefredy.lestounes.constants.MediaStartAction;
import com.lefredy.lestounes.constants.MediaStopAction;
import com.lefredy.lestounes.dtos.playsession.PlaySessionMetricDto;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PlaySessionMetricsTests {

    @Test
    void constructor_setsAllFieldsAndGeneratesId() {
        UUID trackId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Date timeStamp = new Date();

        PlaySessionMetrics metrics = new PlaySessionMetrics(trackId, "Titre", userId, 1000L, 5000L,
                MediaStartAction.START, MediaStopAction.END, timeStamp);

        assertNotNull(metrics.getId());
        assertEquals(trackId, metrics.getTrackId());
        assertEquals("Titre", metrics.getTrackTitle());
        assertEquals(userId, metrics.getUserId());
        assertEquals(1000L, metrics.getStartMs());
        assertEquals(5000L, metrics.getEndMs());
        assertEquals(MediaStartAction.START, metrics.getStartAction());
        assertEquals(MediaStopAction.END, metrics.getStopAction());
        assertEquals(timeStamp, metrics.getTimeStamp());
    }

    @Test
    void constructor_generatesDifferentIds() {
        PlaySessionMetrics first = new PlaySessionMetrics(UUID.randomUUID(), "A", UUID.randomUUID(), 0L, 1L,
                MediaStartAction.SKIP, MediaStopAction.SKIP, new Date());
        PlaySessionMetrics second = new PlaySessionMetrics(UUID.randomUUID(), "B", UUID.randomUUID(), 0L, 1L,
                MediaStartAction.SKIP, MediaStopAction.SKIP, new Date());

        assertNotEquals(first.getId(), second.getId());
    }

    @Test
    void defaultConstructor_leavesFieldsNull() {
        PlaySessionMetrics metrics = new PlaySessionMetrics();

        assertNull(metrics.getId());
        assertNull(metrics.getTrackId());
        assertNull(metrics.getTimeStamp());
    }

    @Test
    void dtoConstructor_copiesFieldsAndConvertsTimeStamp() {
        PlaySessionMetricDto dto = new PlaySessionMetricDto(UUID.randomUUID(), UUID.randomUUID(), 1000L, 5000L,
                MediaStartAction.FOLLOW, MediaStopAction.STOP, 1764100000000L);

        PlaySessionMetrics metrics = new PlaySessionMetrics(dto, "Titre");

        assertNotNull(metrics.getId());
        assertEquals(dto.trackId(), metrics.getTrackId());
        assertEquals("Titre", metrics.getTrackTitle());
        assertEquals(dto.userId(), metrics.getUserId());
        assertEquals(1000L, metrics.getStartMs());
        assertEquals(5000L, metrics.getEndMs());
        assertEquals(MediaStartAction.FOLLOW, metrics.getStartAction());
        assertEquals(MediaStopAction.STOP, metrics.getStopAction());
        assertEquals(new Date(1764100000000L), metrics.getTimeStamp());
    }
}