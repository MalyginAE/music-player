package com.hse.nn.musicplayerdictionary.mapper;

import com.hse.nn.musicplayerdictionary.model.MusicTicket;
import com.hse.nn.musicplayerdictionary.model.dto.request.SaveTicketRequest;
import com.hse.nn.musicplayerdictionary.model.dto.request.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Music;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MusicMapper {

    @Mapping(target = "musicName", source = "trackTitle")
    @Mapping(target = "author", source = "trackAuthor")
    @Mapping(target = "imageId", source = "trackCoverId")
    @Mapping(target = "externalSearchId", source = "ticket_no")
    Music ticketToResponseTicket(MusicTicket musicTicket);
}
