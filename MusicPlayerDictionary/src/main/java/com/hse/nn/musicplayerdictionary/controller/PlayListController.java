package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
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

    @Operation(summary = "Удаление плейлиста пользователю в бд")
    @DeleteMapping("/playlist")
    public ResponseEntity deletePlaylist(@RequestParam String name) {
        log.debug("Got request with name: {}", name);
        playListService.deletePlaylist(name);
//        likeService.saveLike(trackId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение плейлистов пользователя")
    @GetMapping
    public ResponseEntity getPlaylists() {
        log.debug("Got request");
        List<PlaylistResponse> playlists = playListService.getPlaylists();
//        likeService.saveLike(trackId);
        return ResponseEntity.ok(playlists);
    }

    @Operation(summary = "Прикрепление трека к плейлисту")
    @PostMapping("/track")
    public ResponseEntity saveTrackToPlaylist(String name, String trackId) {
        log.debug("Got request with trackId: {}", trackId);
         playListService.addMusicInPlaylist(name, trackId);
//        likeService.saveLike(trackId);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Удаление трека в плейлисте")
    @DeleteMapping("/track")
    public ResponseEntity deleteTrackToPlaylist(String name, String trackId) {
        log.debug("Got request with trackId: {}", trackId);
         playListService.deleteMusicInPlaylist(name, trackId);
//        likeService.saveLike(trackId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Прикрепление трека к плейлисту")
    @GetMapping("/tracks")
    public ResponseEntity findTracksInPlaylist(String name) {
        log.debug("Got request with name: {}", name);
        List<MusicTicketResponse> ticketResponses = playListService.getMusicByPlaylistName(name);
//        likeService.saveLike(trackId);
        return ResponseEntity.ok(ticketResponses);
    }


}
