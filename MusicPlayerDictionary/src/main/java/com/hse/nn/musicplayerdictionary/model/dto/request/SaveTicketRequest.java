package com.hse.nn.musicplayerdictionary.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class SaveTicketRequest {
    @Schema(name = "Заголовок тикета", description = "По нему можно производить поиск")
    private String trackTitle;
    @Schema(name = "Присвоится автоматически")
    private String trackId;
    @Schema(name = "")
    private String trackCoverId;
    @Schema(name = "Автор произведения")
    private String trackAuthor;

    private List<SaveTicketRequest> tickets;
}
