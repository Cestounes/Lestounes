package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.dtos.playsession.PlaySessionMetricDto;
import com.lefredy.lestounes.service.PlaySessionMetricService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v{version}/metrics/PlaySession", version = "1")
public class PlaySessionMetricController {

    @Autowired
    private PlaySessionMetricService playSessionMetricService;

    @PostMapping("")
    public ResponseEntity<Void> createPlaySessionMetric(@Valid @RequestBody PlaySessionMetricDto playSessionMetricDto) {
        playSessionMetricService.createPlaySessionMetric(playSessionMetricDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}