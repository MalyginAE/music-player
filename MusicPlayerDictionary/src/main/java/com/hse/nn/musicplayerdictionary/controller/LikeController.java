package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hse/api/v1/music-player-dictionary/likes")
@RequiredArgsConstructor
@Slf4j
public class LikeController {
private final LikeService likeService;

    @Operation(summary = "Сохранение лайка в бд")
    @PostMapping("/like")
    public ResponseEntity like(@RequestParam String trackId) {
        log.debug("Got request with id: {}", trackId);
        likeService.saveLike(trackId);
        return ResponseEntity.ok().build();
    }
}
