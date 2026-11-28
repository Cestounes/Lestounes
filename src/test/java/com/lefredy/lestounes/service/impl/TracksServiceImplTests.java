package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.dtos.user.TracksFilterDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.repository.TracksRepository;
import com.lefredy.lestounes.schemas.Tracks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TracksServiceImplTests {

    @Mock
    private TracksRepository tracksRepository;
    @InjectMocks
    private TracksServiceImpl tracksServiceImpl;
    @Mock
    private MongoTemplate mongoTemplate;
    @TempDir
    Path musicFolder;
    @Test
    void getTracksFiltered_returnsTheTracksFoundByMongo() {

        TracksFilterDto tracksFilterDto = new TracksFilterDto();
        tracksFilterDto.setPage(0);
        tracksFilterDto.setPageNbLimit(10);
        List<Tracks> tracks = List.of(new Tracks(), new Tracks());
        when(mongoTemplate.find(any(Query.class), eq(Tracks.class))).thenReturn(tracks);

        List<Tracks> result = tracksServiceImpl.getTracksFiltered(tracksFilterDto);

        assertEquals(2, result.size());
        verify(mongoTemplate).find(any(Query.class), eq(Tracks.class));
    }
    @Test
    void getTrackById_found_returnsTheTrack() {
        UUID id = UUID.randomUUID();
        Tracks track = new Tracks();
        when(tracksRepository.findById(id)).thenReturn(Optional.of(track));

        Tracks result = tracksServiceImpl.getTrackById(id);

        assertSame(track, result);
    }

    @Test
    void getTrackById_notFound_throws() {
        UUID id = UUID.randomUUID();
        when(tracksRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DocumentNotFoundException.class, () -> tracksServiceImpl.getTrackById(id));
    }
    @Test
    void getTrackFileById_fileMissing_throws() {
        UUID id = UUID.randomUUID();
        Tracks track = new Tracks();
        track.setPath("missing.mp3");
        when(tracksRepository.findById(id)).thenReturn(Optional.of(track));
        ReflectionTestUtils.setField(tracksServiceImpl, "musicRoot", musicFolder.toString());

        assertThrows(DocumentNotFoundException.class, () -> tracksServiceImpl.getTrackFileById(id));
    }
    @Test
    void getTracksFiltered_withEveryFilter_stillReturnsTheTracks() {
        TracksFilterDto tracksFilterDto = new TracksFilterDto();
        tracksFilterDto.setTitle("Roach");
        tracksFilterDto.setArtists("Glad");
        tracksFilterDto.setAlbum("Single");
        tracksFilterDto.setAlbumArtist("Glad");
        tracksFilterDto.setGenre("JAZZ");
        tracksFilterDto.setUpdatedAt(new Date());
        tracksFilterDto.setPage(1);
        tracksFilterDto.setPageNbLimit(5);

        when(mongoTemplate.find(any(Query.class), eq(Tracks.class))).thenReturn(List.of(new Tracks()));

        List<Tracks> result = tracksServiceImpl.getTracksFiltered(tracksFilterDto);

        assertEquals(1, result.size());
        verify(mongoTemplate).find(any(Query.class), eq(Tracks.class));
    }
}
