package com.hse.player.musicplayerdictionary.service;

import com.hse.player.musicplayerdictionary.Ticket;
import com.hse.player.musicplayerdictionary.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl {

    private final TicketRepository ticketRepository;
//    private final TicketMapper ticketMapper;

//    public Ticket saveIndex(SaveTicketIndexRequest request){
//        return ticketRepository.save(ticketMapper.ticketDtoToTicket(request.getTicketDto()));
//    }
//
//    public List<Ticket> saveBulkIndex(SaveTicketBulkIndexRequest request) {
//        List<Ticket> tickets = request
//                .getTickets()
//                .stream()
//                .map(ticketDto -> ticketMapper.ticketDtoToTicket(ticketDto))
//                .collect(Collectors.toList());
//        return (List<Ticket>) ticketRepository.saveAll(tickets);
//    }
//
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