package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.repository.MusicTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl {
    private final MusicTicketRepository musicTicketRepository;
    private final TicketMapper ticketMapper;

//    public Ticket saveIndex(SaveTicketIndexRequest request){
//        return musicTicketRepository.save(ticketMapper.ticketDtoToTicket(request.getTicketDto()));
//    }

    public List<MusicTicket> saveBulk(SaveTicketRequest request) {
        List<MusicTicket> tickets = request
                .getTickets()
                .stream()
                .map(ticketMapper::ticketDtoToTicket)
                .collect(Collectors.toList());
        return (List<MusicTicket>) musicTicketRepository.saveAll(tickets);
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