package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;


@ExtendWith(MockitoExtension.class)
class TicketServiceWithNativeQueryTest {
    @Mock
    private ElasticsearchOperations elasticsearchOperations;

    @Mock
    private TicketMapper ticketMapper;

    @Test
    void getMusicTickets() {
        //given
        TicketServiceWithNativeQuery ticketServiceWithNativeQuery = new TicketServiceWithNativeQuery(elasticsearchOperations, ticketMapper);
        //when
        Assertions.assertThrows(NullPointerException.class, () -> ticketServiceWithNativeQuery.getMusicTickets(null, 0));

    }
}