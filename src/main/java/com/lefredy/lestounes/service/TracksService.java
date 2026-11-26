package com.lefredy.lestounes.service;

import org.springframework.core.io.FileSystemResource;
import com.lefredy.lestounes.dtos.user.TracksFilterDto;
import com.lefredy.lestounes.schemas.Tracks;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.UUID;

public interface TracksService {

    List<Tracks> getTracksFiltered(TracksFilterDto tracksFilterDto);

    Tracks getTrackById(UUID id);

    Resource getTrackFileById(UUID id);

}
