package com.hse.nn.musicplayerdictionary.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SaveTicketRequest {
    @Schema( description = "По нему можно производить поиск")
    private String trackTitle;
    @Schema(description = "Присвоиться автоматически")
    private String trackId;
    @Schema(description = "id image")
    private String trackCoverId;
    @Schema(description = "Автор произведения")
    private String trackAuthor;

    private List<SaveTicketRequest> tickets;
}
