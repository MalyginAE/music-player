package com.hse.nn.musicplayerdictionary.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hse/api/v1/music-player-dictionary/playlist")
@RequiredArgsConstructor
@Slf4j
public class PlayListController {

    @Operation(summary = "Сохранение лайка в бд")
    @PostMapping("/like")
    public ResponseEntity like(@RequestParam String trackId) {
        log.debug("Got request with id: {}", trackId);
//        likeService.saveLike(trackId);
        return ResponseEntity.ok().build();
    }


}
