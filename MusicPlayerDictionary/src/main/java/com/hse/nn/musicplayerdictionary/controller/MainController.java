package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.mapper.MusicMapper;
import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Music;
import com.hse.nn.musicplayerdictionary.repository.postgres.MusicRepository;
import com.hse.nn.musicplayerdictionary.service.TicketServiceImpl;
import com.hse.nn.musicplayerdictionary.service.TicketServiceWithNativeQuery;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hse/api/v1/music-player-dictionary")
@RequiredArgsConstructor
@Slf4j
public class MainController {
    private final TicketServiceImpl ticketService;
    private final TicketServiceWithNativeQuery ticketServiceWithNativeQuery;
    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;

    @GetMapping("/search")
    @Operation(summary = "Поиск трека по заголовку")
    public ResponseEntity<List<MusicTicketResponse>> get(@RequestParam("trackTitle") String title,
                                                         @RequestParam(value = "pageNumber", required = false, defaultValue = "0")
                                                         String pageNumber) {
        log.debug("Got request with title: {}", title);
        List<MusicTicketResponse> musicTickets = ticketServiceWithNativeQuery.getMusicTickets(title, Integer.parseInt(pageNumber));
        return ResponseEntity.ok(musicTickets);
    }

    @Operation(summary = "Сохранение трека в бд")
    @PostMapping("/save")
    public ResponseEntity<List<MusicTicket>> save(@RequestBody SaveTicketRequest request) {
        log.debug("Got request with body: {}", request);
        List<MusicTicket> saved = ticketService.saveBulk(request);
        List<Music> musicList = saved.stream().map(musicMapper::ticketToResponseTicket).toList();
        musicRepository.saveAll(musicList);
        return ResponseEntity.ok(saved);
    }


//    @GetMapping(value = "/image/{id}", produces = "image/png")
//    public @ResponseBody byte[] saveLike(@PathVariable String id) throws IOException {
//        log.debug("Got request with id: {}", id);
//        InputStream in = getClass().getResourceAsStream("/static/image/" + id);
//        return Objects.requireNonNull(in).readAllBytes();
//    }
}
