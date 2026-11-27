package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.dtos.user.PlaylistsDto;
import com.lefredy.lestounes.dtos.user.PlaylistsUpdateDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.schemas.Playlists;
import com.lefredy.lestounes.service.PlaylistsService;

import java.util.UUID;

public class PlaylistsServiceImpl implements PlaylistsService {
    @Override
    public Playlists getPlaylist() throws DocumentNotFoundException {
        return null;
    }

    @Override
    public Playlists getPlaylistsById(UUID id) throws DocumentNotFoundException {
        return null;
    }

    @Override
    public Playlists createPlaylist(PlaylistsDto playlistDto) throws DocumentNotFoundException {
        return null;
    }

    @Override
    public Playlists updatePlaylist(PlaylistsUpdateDto playlistsUpdateDto) throws DocumentNotFoundException {
        return null;
    }

    @Override
    public void deletePlaylists(UUID id) throws DocumentNotFoundException {

    }
}
