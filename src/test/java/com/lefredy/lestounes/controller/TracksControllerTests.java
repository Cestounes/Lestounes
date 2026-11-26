package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.config.WebConfiguration;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.schemas.Tracks;
import com.lefredy.lestounes.service.TracksService;
import com.lefredy.lestounes.service.UserService;
import com.lefredy.lestounes.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(TracksController.class)
@Import(WebConfiguration.class)
public class TracksControllerTests {

    @Autowired
    MockMvc mockMvc;
    @TempDir
    Path tempDir;
    @MockitoBean
    JwtUtil jwtUtil;
    @MockitoBean
    TracksService tracksService;
    @MockitoBean
    UserService userService;

    @Test
    void getTracksFilter_returns200AndTheTracks() throws Exception {
        Tracks track = new Tracks();
        track.setTitle("One More Time");
        when(tracksService.getTracksFiltered(any())).thenReturn(List.of(track));

        mockMvc.perform(get("/api/v1/track/")
                        .param("genre", "house")
                        .param("title", "One More Time")
                        .param("album", "Discovery")
                        .param("artist", "Daft Punk")
                        .param("albumArtist", "Daft Punk")
                        .param("page", "0")
                        .param("pageNbLimit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("One More Time"));

        verify(tracksService).getTracksFiltered(any());
    }
    @Test
    void getOne_unknownId_returns404() throws Exception {
        when(tracksService.getTrackById(any())).thenThrow(new DocumentNotFoundException());

        mockMvc.perform(get("/api/v1/track/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getTracksFile_returns200AndAnMp3() throws Exception {
        byte[] fakeMp3 = {1, 2, 3};
        when(tracksService.getTrackFileById(any())).thenReturn(fakeFile(fakeMp3));

        mockMvc.perform(get("/api/v1/track/file/" + UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("audio/mpeg"))
                .andExpect(content().bytes(fakeMp3));
    }

    private FileSystemResource fakeFile(byte[] content) throws IOException {
        Path file = tempDir.resolve("song.mp3");
        Files.write(file, content);
        return new FileSystemResource(file);
    }
}
