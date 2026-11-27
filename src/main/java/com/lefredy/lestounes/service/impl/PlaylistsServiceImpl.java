package com.lefredy.lestounes.service.impl;

import com.lefredy.lestounes.dtos.user.PlaylistsDto;
import com.lefredy.lestounes.exceptions.DocumentNotFoundException;
import com.lefredy.lestounes.repository.PlaylistsRepository;
import com.lefredy.lestounes.schemas.Playlists;
import com.lefredy.lestounes.service.PlaylistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaylistsServiceImpl implements PlaylistsService {

    @Autowired
    private PlaylistsRepository playlistsRepository;

    @Override
    public List<Playlists> getPlaylist() throws DocumentNotFoundException {
        return playlistsRepository.findAll();
    }

    @Override
    public Playlists getPlaylistsById(UUID id) throws DocumentNotFoundException {
        return playlistsRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public Playlists createPlaylist(PlaylistsDto playlistDto) throws DocumentNotFoundException {
        Playlists newPlaylist =  new Playlists();
        newPlaylist.setName(playlistDto.getName());
        newPlaylist.setTracks(playlistDto.getTracks());
        return newPlaylist;
    }

    @Override
    public Playlists updatePlaylistById(UUID id, PlaylistsDto playlistsDto) throws DocumentNotFoundException {
        Playlists playlist = getPlaylistsById(id);
        playlist.setName(playlistsDto.getName());
        playlist.setTracks(playlistsDto.getTracks());
        return playlistsRepository.save(playlist);
    }

    @Override
    public void deletePlaylists(UUID id) throws DocumentNotFoundException {
        if (!playlistsRepository.existsById(id)) {
            throw new DocumentNotFoundException();
        }
        playlistsRepository.deleteById(id);
    }
}
