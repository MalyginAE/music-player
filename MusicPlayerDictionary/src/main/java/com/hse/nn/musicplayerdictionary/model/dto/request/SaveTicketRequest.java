package com.hse.nn.musicplayerdictionary.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SaveTicketRequest {
    private String trackTitle;
    private String trackId;
    private String trackCoverId;
    private String trackAuthor;

    private List<SaveTicketRequest> tickets;
}
