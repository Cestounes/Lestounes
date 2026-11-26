package com.lefredy.lestounes.schemas;
import com.lefredy.lestounes.dtos.user.TracksDto;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TracksTests {
    @Test
    void constructor_copiesEveryFieldFromTheDto() {
        Date date = new Date();
        TracksDto dto = new TracksDto();
        dto.setPath("Glad/Single/Roach.mp3");
        dto.setTitle("Roach");
        dto.setLength(180L);
        dto.setArtists(List.of("Glad"));
        dto.setAlbum("Single");
        dto.setAlbumArtist("Glad");
        dto.setComposer("Glad");
        dto.setDate(date);
        dto.setGenres(List.of("jazz"));
        dto.setTrackNumber(3);
        dto.setDiscNumber(1);
        dto.setComment("nice");

        Tracks track = new Tracks(dto);

        assertNotNull(track.getId());
        assertEquals("Glad/Single/Roach.mp3", track.getPath());
        assertEquals("Roach", track.getTitle());
        assertEquals(180L, track.getLength());
        assertEquals(List.of("Glad"), track.getArtists());
        assertEquals("Single", track.getAlbum());
        assertEquals("Glad", track.getAlbumArtist());
        assertEquals("Glad", track.getComposer());
        assertEquals(date, track.getDate());
        assertEquals(List.of("jazz"), track.getGenres());
        assertEquals(3, track.getTrackNumber());
        assertEquals(1, track.getDiscNumber());
        assertEquals("nice", track.getComment());
        assertNotNull(track.getUpdatedAt());
        assertFalse(track.getDeleted());
    }
    @Test
    void constructor_missingTrackAndDiscNumber_defaultsToZero() {
        TracksDto dto = new TracksDto();
        dto.setPath("song.mp3");

        Tracks track = new Tracks(dto);

        assertEquals(0, track.getTrackNumber());
        assertEquals(0, track.getDiscNumber());
    }
}
