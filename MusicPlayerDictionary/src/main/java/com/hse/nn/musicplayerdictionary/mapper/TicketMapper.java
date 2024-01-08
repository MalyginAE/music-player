package com.hse.nn.musicplayerdictionary.mapper;

import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketMapper  {
    @Mapping(target = "trackTitle", source = "trackTitle")
    MusicTicket ticketDtoToTicket(SaveTicketRequest ticket);
}
