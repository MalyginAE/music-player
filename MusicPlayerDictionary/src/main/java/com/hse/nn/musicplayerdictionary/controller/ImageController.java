package com.hse.nn.musicplayerdictionary.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping("/hse/api/v1/music-player-dictionary")
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    @Operation(summary = "Получение изображения по id")
    @GetMapping(value = "/image/{id}", produces = "image/png")
    public @ResponseBody byte[] getImage(@PathVariable String id) throws IOException {
        log.debug("Got request with id: {}", id);
        InputStream in = getClass().getResourceAsStream("/static/image/" + id);
        return Objects.requireNonNull(in).readAllBytes();
    }
}
