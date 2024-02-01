package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.repository.MusicTicketRepository;
import com.hse.nn.musicplayerdictionary.service.TicketServiceImpl;
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
public class MainController {
    private final TicketServiceImpl ticketService;

    @GetMapping("/search")
    public ResponseEntity<List<MusicTicket>> get(@RequestParam("trackTitle") String title) {
        log.debug("Got request with title: {}", title);
        List<MusicTicket> musicTickets = ticketService.getMusicTickets(title);
        return ResponseEntity.ok(musicTickets);
    }

    @PostMapping("/save")
    public ResponseEntity<List<MusicTicket>> save(@RequestBody SaveTicketRequest request) {
        log.debug("Got request with body: {}", request);
        List<MusicTicket> saved = ticketService.saveBulk(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping(value = "/music/{id}", produces = "audio/mp3")
    public @ResponseBody byte[] getMusic(@PathVariable String id) throws IOException {
        log.debug("Got request with id: {}", id);
        InputStream in = getClass().getResourceAsStream("/static/" + id + ".mp3");
        return Objects.requireNonNull(in).readAllBytes();
    }

    @GetMapping(value = "/image/{id}", produces = "image/png")
    public @ResponseBody byte[] getImage(@PathVariable String id) throws IOException {
        log.debug("Got request with id: {}", id);
        InputStream in = getClass().getResourceAsStream("/static/" + id + ".mp3");
        return Objects.requireNonNull(in).readAllBytes();
    }
}
