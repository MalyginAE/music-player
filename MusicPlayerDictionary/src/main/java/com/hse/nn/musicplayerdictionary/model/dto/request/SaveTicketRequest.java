package com.hse.nn.musicplayerdictionary.model.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class SaveTicketRequest {
    private List<SaveTicketRequest> tickets;

    public List<SaveTicketRequest> getTickets() {
        return tickets;
    }
}
