package com.lefredy.lestounes.schemas;

import com.lefredy.lestounes.constants.eMediaStartAction;
import com.lefredy.lestounes.constants.eMediaStopAction;
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
                eMediaStartAction.START, eMediaStopAction.END, timeStamp);

        assertNotNull(metrics.getId());
        assertEquals(trackId, metrics.getTrackId());
        assertEquals("Titre", metrics.getTrackTitle());
        assertEquals(userId, metrics.getUserId());
        assertEquals(1000L, metrics.getStartMs());
        assertEquals(5000L, metrics.getEndMs());
        assertEquals(eMediaStartAction.START, metrics.getStartAction());
        assertEquals(eMediaStopAction.END, metrics.getStopAction());
        assertEquals(timeStamp, metrics.getTimeStamp());
    }

    @Test
    void constructor_generatesDifferentIds() {
        PlaySessionMetrics first = new PlaySessionMetrics(UUID.randomUUID(), "A", UUID.randomUUID(), 0L, 1L,
                eMediaStartAction.SKIP, eMediaStopAction.SKIP, new Date());
        PlaySessionMetrics second = new PlaySessionMetrics(UUID.randomUUID(), "B", UUID.randomUUID(), 0L, 1L,
                eMediaStartAction.SKIP, eMediaStopAction.SKIP, new Date());

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
    void dtoConstructor_copiesAllFields() {
        Date timeStamp = new Date();
        PlaySessionMetricDto dto = new PlaySessionMetricDto(UUID.randomUUID(), "Titre", UUID.randomUUID(), 1000L, 5000L,
                eMediaStartAction.FOLLOW, eMediaStopAction.STOP, timeStamp);

        PlaySessionMetrics metrics = new PlaySessionMetrics(dto);

        assertNotNull(metrics.getId());
        assertEquals(dto.trackId(), metrics.getTrackId());
        assertEquals("Titre", metrics.getTrackTitle());
        assertEquals(dto.userId(), metrics.getUserId());
        assertEquals(1000L, metrics.getStartMs());
        assertEquals(5000L, metrics.getEndMs());
        assertEquals(eMediaStartAction.FOLLOW, metrics.getStartAction());
        assertEquals(eMediaStopAction.STOP, metrics.getStopAction());
        assertEquals(timeStamp, metrics.getTimeStamp());
    }
}