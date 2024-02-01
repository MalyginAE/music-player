package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.repository.MusicTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl {
    private final MusicTicketRepository musicTicketRepository;
    private final TicketMapper ticketMapper;

    public List<MusicTicket> saveBulk(SaveTicketRequest request) {
        List<MusicTicket> tickets = request
                .getTickets()
                .stream()
                .map(ticketMapper::ticketDtoToTicket)
                .collect(Collectors.toList());
        return (List<MusicTicket>) musicTicketRepository.saveAll(tickets);
    }

    public List<MusicTicket> getMusicTickets(String title) {
        return musicTicketRepository.findByTrackTitleContaining(title, PageRequest.of(1,10));
    }

//    public FindByNameContainingResponse findByNameContaining(String name){
//        List<TicketDto> ticketDtos = ticketRepository
//                .findByPassengerNameContaining(name)
//                .stream()
//                .map(ticket -> ticketMapper.ticketToTicketDto(ticket))
//                .collect(Collectors.toList());
//        return FindByNameContainingResponse
//                .builder()
//                .tickets(ticketDtos)
//                .build();
//
//    }
}