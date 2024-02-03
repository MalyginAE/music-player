package com.hse.nn.musicplayerdictionary.mapper;

import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.model.dto.request.response.MusicTicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketMapper  {
    @Mapping(target = "trackTitle", source = "trackTitle")
    MusicTicket ticketDtoToTicket(SaveTicketRequest ticket);

    @Mapping(target = "trackTitle", source = "trackTitle")
    MusicTicketResponse ticketToResponseTicket(MusicTicket ticket);
}
