package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("nativeTicketService")
@RequiredArgsConstructor
@Slf4j
public class TicketServiceWithNativeQuery {
    private final ElasticsearchOperations operations;
    private final TicketMapper ticketMapper;

    public List<MusicTicketResponse> getMusicTickets(String title, int pageNumber) {
        Pageable pageRequest = PageRequest.of(pageNumber, 10);
        var query = NativeQuery.builder()
                .withQuery(q -> q
                        .matchPhrase(m -> m
                                .field("track_title")
                                .slop(1)
                                .query(title)
                        )
                )
                .withQuery(q ->
                        q.matchPhrasePrefix(m -> m
                                .field("track_title")
                                .slop(1)
                                .query(title)))
                .withPageable(pageRequest)
                .build();

        SearchHits<MusicTicket> searchHits = operations.search(query, MusicTicket.class);
        log.debug("SearchHits: " + searchHits);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .map(ticketMapper::ticketToResponseTicket)
                .toList();
    }
}
