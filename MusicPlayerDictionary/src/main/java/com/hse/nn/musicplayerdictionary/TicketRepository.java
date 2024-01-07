package com.hse.nn.musicplayerdictionary;


import com.hse.nn.musicplayerdictionary.model.Ticket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface TicketRepository extends ElasticsearchRepository<Ticket, String> {
    List<Ticket> findByPassengerNameContaining(String name);
}