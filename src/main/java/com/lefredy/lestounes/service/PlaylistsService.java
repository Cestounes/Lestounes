package com.lefredy.lestounes.service;


import com.lefredy.lestounes.dtos.user.PlaylistsDto;
import com.lefredy.lestounes.dtos.user.PlaylistsUpdateDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.schemas.Playlists;

import java.util.UUID;

public interface PlaylistsService {

    Playlists getPlaylist() throws DocumentNotFoundException;

    Playlists getPlaylistsById(UUID id) throws DocumentNotFoundException;

    Playlists createPlaylist(PlaylistsDto playlistDto) throws DocumentNotFoundException;

    Playlists updatePlaylist(PlaylistsUpdateDto playlistsUpdateDto) throws DocumentNotFoundException;

    void deletePlaylists(UUID id) throws DocumentNotFoundException;
}
