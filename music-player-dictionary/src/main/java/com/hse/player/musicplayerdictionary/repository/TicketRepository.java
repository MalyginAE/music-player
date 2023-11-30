package com.hse.player.musicplayerdictionary.repository;

import com.hse.player.musicplayerdictionary.Ticket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TicketRepository extends ElasticsearchRepository<Ticket, String> {
    List<Ticket> findByPassengerNameContaining(String name);
}