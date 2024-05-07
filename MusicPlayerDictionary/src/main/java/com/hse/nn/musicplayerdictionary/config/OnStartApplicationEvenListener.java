package com.hse.nn.musicplayerdictionary.config;

import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.repository.MusicTicketRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OnStartApplicationEvenListener {
    public static final List<MusicTicket> BASE_TICKETS = List.of(
            MusicTicket.builder()
                    .trackId("1")
                    .trackTitle("Улетай")
                    .trackCoverId("1")
                    .trackAuthor("Три дня дождя")
                    .build(),
            MusicTicket.builder()
                    .trackId("2")
                    .trackTitle("Утренняя гимнастика")
                    .trackCoverId("2")
                    .trackAuthor("Владимир Высоцкий")
                    .build(),
            MusicTicket.builder()
                    .trackId("3")
                    .trackTitle("Дым")
                    .trackCoverId("3")
                    .trackAuthor("Егор Крид feat. JONY")
                    .build(),
            MusicTicket.builder()
                    .trackId("4")
                    .trackTitle("I Want It That Way")
                    .trackCoverId("4")
                    .trackAuthor("PHURS ,BOOTY LEAK, Michelle Ray")
                    .build(),
            MusicTicket.builder()
                    .trackId("5")
                    .trackTitle("I Want It That Way")
                    .trackCoverId("5")
                    .trackAuthor("PHURS ,BOOTY LEAK, Michelle Ray")
                    .build()
    );
    private static final Logger log = LoggerFactory.getLogger(OnStartApplicationEvenListener.class);
    private final MusicTicketRepository musicTicketRepository;

    @EventListener(classes = ApplicationStartedEvent.class)
    public void onStartApplication() {
        if (musicTicketRepository.findAll().isEmpty()) {
            log.info("initialize elasticsearch with base music data");
            musicTicketRepository.saveAll(BASE_TICKETS);
        }
    }
}
