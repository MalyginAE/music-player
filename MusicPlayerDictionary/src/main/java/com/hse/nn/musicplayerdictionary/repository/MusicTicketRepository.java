package com.hse.nn.musicplayerdictionary.repository;


import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface MusicTicketRepository extends ElasticsearchRepository<MusicTicket, String> {
    List<MusicTicket> findByTrackTitleContaining(String name, Pageable pageable);
}