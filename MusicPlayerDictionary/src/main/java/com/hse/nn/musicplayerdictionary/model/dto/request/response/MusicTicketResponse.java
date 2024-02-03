package com.hse.nn.musicplayerdictionary.model.dto.request.response;


public record MusicTicketResponse(String trackId,
                                  String trackTitle,
                                  String trackAuthor,
                                  String trackCoverId) {
}
