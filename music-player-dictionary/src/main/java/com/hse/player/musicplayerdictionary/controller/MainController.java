package com.hse.player.musicplayerdictionary.controller;

import com.hse.player.musicplayerdictionary.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
private final TicketRepository ticketRepository;
    @GetMapping("/api")
    void get(){
        System.out.println();
    }
}
