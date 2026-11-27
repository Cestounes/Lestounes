package com.lefredy.lestounes.controller;

import com.lefredy.lestounes.dtos.user.PlaylistsDto;
import com.lefredy.lestounes.schemas.Playlists;
import com.lefredy.lestounes.service.PlaylistsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v{version}/library/playlist",version = "1")
public class PlaylistsController {

    @Autowired
    private PlaylistsService playlistsService;

    @GetMapping("")
    public ResponseEntity<List<Playlists>> getPlaylist(@Valid @ModelAttribute PlaylistsDto playlistsDto) {
        return ResponseEntity.ok(playlistsService.getPlaylist());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlists> getPlaylistById(@PathVariable UUID id){
        return ResponseEntity.ok(playlistsService.getPlaylistsById(id));
    }

    @PostMapping("/user/post")
    public void postPlaylist() {

    }

    @PatchMapping("/user/{id}")
    public void patchPlaylistById(@PathVariable UUID id) {

    }

    @DeleteMapping("/user/delete/{id}")
    public void deletePlaylistById(@PathVariable UUID id) {

    }

    @DeleteMapping("/admin/delete/{id}")
    public void adminDeletePlaylistById(@PathVariable UUID id){

    }

    @PatchMapping("/admin/update/{id}")
    public void adminPatchPlaylistById(@PathVariable UUID id) {

    }


}
