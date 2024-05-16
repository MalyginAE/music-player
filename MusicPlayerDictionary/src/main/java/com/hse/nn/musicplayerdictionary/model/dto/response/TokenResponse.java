package com.hse.nn.musicplayerdictionary.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TokenResponse {
    @Schema( description = "refreshToken")
    private String refreshToken;
    @Schema( description = "accessToken")
    private String accessToken;

}
