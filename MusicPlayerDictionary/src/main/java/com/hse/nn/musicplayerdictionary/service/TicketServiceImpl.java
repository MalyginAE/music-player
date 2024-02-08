package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.model.dto.request.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.repository.MusicTicketRepository;
import lombok.RequiredArgsConstructor;
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

    public List<MusicTicketResponse> getMusicTickets(String title) {
        return musicTicketRepository.findByTrackTitleContaining(title, PageRequest.of(0,10))
                .stream()
                .map(ticketMapper::ticketToResponseTicket)
                .toList();
    }

    public List<MusicTicketResponse> buildPopularTickets() {
        MusicTicketResponse aigel = new MusicTicketResponse("1", "Пыяла", "АИГЕЛ", "1");
        MusicTicketResponse dymok = new MusicTicketResponse("2", "Дымок", "Ицык Цыпер, Игорь Дыба", "1");
//        MusicTicketResponse aigel = new MusicTicketResponse("3", "Пыяла", "АИГЕЛ", "1");
//        MusicTicketResponse aigel = new MusicTicketResponse("4", "Пыяла", "АИГЕЛ", "1");
        return List.of(aigel,dymok);
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