package com.hse.player.musicplayerdictionary.repository;

import com.hse.player.musicplayerdictionary.MusicTicket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MusicTicketRepository extends ElasticsearchRepository<MusicTicket, String> {
    List<MusicTicket> findByTrackTitleContaining(String name);
}