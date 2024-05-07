package com.hse.nn.musicplayerdictionary.model.dto.response;

import java.util.List;

public record PlaylistResponse(List<MusicTicketResponse> tickets, String playlistName) {
}
