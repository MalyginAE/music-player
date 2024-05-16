package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
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
        title = title.replaceAll("\\s+","");
        return musicTicketRepository.findByTrackTitleContaining(title, PageRequest.of(0,10))
                .stream()
                .map(ticketMapper::ticketToResponseTicket)
                .toList();
    }

    public List<MusicTicketResponse> buildPopularTickets() {
        MusicTicketResponse yletai = new MusicTicketResponse("1", "Улетай", "Три дня дождя", "1");
        MusicTicketResponse gimanstica = new MusicTicketResponse("2", "Гимнастика", "Владимир Высоцкий", "2");
        MusicTicketResponse dym = new MusicTicketResponse("3", "Дым", "Егор Крид, JONY", "3");
//        MusicTicketResponse aigel = new MusicTicketResponse("3", "Пыяла", "АИГЕЛ", "1");
//        MusicTicketResponse aigel = new MusicTicketResponse("4", "Пыяла", "АИГЕЛ", "1");
        return List.of(yletai,gimanstica,dym);
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