package com.hse.nn.musicplayerdictionary.model.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class SaveTicketRequest {
    private String trackTitle;

    private List<SaveTicketRequest> tickets;

    public String getTrackTitle() {
        return trackTitle;
    }
}
