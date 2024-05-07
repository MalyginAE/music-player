package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.service.TicketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/hse/api/v1/music-player-dictionary")
@RequiredArgsConstructor
@Slf4j
public class MusicController {
    private final TicketServiceImpl ticketService;

    @Operation(summary = "Получение популярной музыки", description = "Вся музыка на моках и не является актуальной.")
    @GetMapping("/music/popular")
    public ResponseEntity<List<MusicTicketResponse>> getPopularMusic() {
        log.debug("Got request to get popular info");
        List<MusicTicketResponse> tickets = ticketService.buildPopularTickets();
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Недавно выпущенная музыка")
    @GetMapping("/music/new")
    public ResponseEntity<List<MusicTicketResponse>> getNewMusic() {
        log.debug("Got request to get new info");
        List<MusicTicketResponse> tickets = ticketService.buildPopularTickets();
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Получение музыки по id")
    @GetMapping(value = "/music/{id}", produces = "audio/mp3")
    public @ResponseBody byte[] getMusic(@PathVariable String id) throws IOException {
        log.debug("Got request with id: {}", id);
        InputStream in = getClass().getResourceAsStream("/static/music/" + id);
        return Objects.requireNonNull(in).readAllBytes();
    }
}
