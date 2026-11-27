package com.lefredy.lestounes.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v{version}/playlist",version = "1")
public class PlaylistsController {

    @GetMapping("")
    public void getPlaylist() {

    }

    @GetMapping("/{id}")
    public void getPlaylistById(@PathVariable UUID id){

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
