package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.model.dto.response.PlaylistResponse;
import com.hse.nn.musicplayerdictionary.service.PlayListService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/hse/api/v1/music-player-dictionary/playlists")
@RequiredArgsConstructor
@Slf4j
public class PlayListController {
private final PlayListService playListService;

    @Operation(summary = "Добавление плейлиста пользователю в бд")
    @PostMapping("/playlist")
    public ResponseEntity createPlaylist(@RequestParam String name) {
        log.debug("Got request with name: {}", name);
        playListService.createPlaylist(name);
//        likeService.saveLike(trackId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение плейлистов пользователя")
    @GetMapping()
    public ResponseEntity getPlaylists() {
        log.debug("Got request");
        List<PlaylistResponse> playlists = playListService.getPlaylists();
//        likeService.saveLike(trackId);
        return ResponseEntity.ok(playlists);
    }


}
