package com.hse.nn.musicplayerdictionary.mapper;

import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Music;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketMapper  {
    @Mapping(target = "trackTitle", source = "trackTitle")
    MusicTicket ticketDtoToTicket(SaveTicketRequest ticket);

    @Mapping(target = "trackTitle", source = "trackTitle")
    MusicTicketResponse ticketToResponseTicket(MusicTicket ticket);

    @Mapping(target = "trackTitle", source = "musicName")
    @Mapping(target = "trackAuthor", source = "author")
    @Mapping(target = "trackCoverId", source = "imageId")
    @Mapping(target = "trackId", source = "id")
//    @Mapping(target = "ticket_no", source = "externalSearchId")
    MusicTicketResponse ticketToResponseTickets(Music music);
}
