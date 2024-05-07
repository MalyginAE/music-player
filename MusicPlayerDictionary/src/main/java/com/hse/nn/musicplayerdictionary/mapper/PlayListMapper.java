package com.hse.nn.musicplayerdictionary.mapper;

import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.model.dto.response.PlaylistResponse;
import com.hse.nn.musicplayerdictionary.model.entity.PlayList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PlayListMapper {

    @Mapping(target = "playlistName", source = "playlistName")
    PlaylistResponse ticketToResponseTicket(PlayList playList);
}
