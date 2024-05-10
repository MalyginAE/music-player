package com.hse.nn.musicplayerdictionary.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record PlaylistResponse( @Schema( description = "Имя плейлиста") String playlistName) {
}
