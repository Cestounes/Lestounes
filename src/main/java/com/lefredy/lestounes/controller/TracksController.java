package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.dtos.user.TracksFilterDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.schemas.Tracks;
import com.lefredy.lestounes.service.TracksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v{version}/library/track",version = "1")
public class TracksController {

    @Autowired
    private TracksService tracksService;

    static final MediaType Audio_MP = MediaType.parseMediaType("audio/mpeg");

    @GetMapping("")
    public ResponseEntity<List<Tracks>> getTracksFilter(@Valid @ModelAttribute TracksFilterDto tracksFilterDto) {
        return ResponseEntity.ok(tracksService.getTracksFiltered(tracksFilterDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tracks> getOne(@PathVariable UUID id) throws DocumentNotFoundException {
        return ResponseEntity.ok(tracksService.getTrackById(id));
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<Resource> getTracksFile(@PathVariable UUID id) throws DocumentNotFoundException {
        Resource resource = tracksService.getTrackFileById(id);

        ContentDisposition disposition = ContentDisposition.inline()
                .filename(resource.getFilename(), StandardCharsets.UTF_8)
                .build();

        return ResponseEntity.ok()
                .contentType(Audio_MP)
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition.toString())
                .body(resource);

    }
}
