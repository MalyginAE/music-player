package com.hse.nn.musicplayerdictionary.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class LikeResponse {
    @Schema( description = "По нему можно производить поиск")
    private String trackTitle;
}
