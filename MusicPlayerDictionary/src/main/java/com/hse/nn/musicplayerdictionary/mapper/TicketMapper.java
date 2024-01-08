package com.hse.nn.musicplayerdictionary.mapper;

import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import org.mapstruct.Mapper;

@Mapper
public interface TicketMapper  {
    MusicTicket ticketDtoToTicket(SaveTicketRequest ticket);
}
