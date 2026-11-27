package com.lefredy.lestounes.service;


import com.lefredy.lestounes.dtos.user.PlaylistsDto;
import com.lefredy.lestounes.dtos.user.PlaylistsUpdateDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.schemas.Playlists;

import java.util.List;
import java.util.UUID;

public interface PlaylistsService {

    List<Playlists> getPlaylist() throws DocumentNotFoundException;

    Playlists getPlaylistsById(UUID id) throws DocumentNotFoundException;

    Playlists createPlaylist(PlaylistsDto playlistDto) throws DocumentNotFoundException;

    Playlists updatePlaylistById(UUID id, PlaylistsDto playlistsDto) throws DocumentNotFoundException;

    void deletePlaylists(UUID id) throws DocumentNotFoundException;
}
