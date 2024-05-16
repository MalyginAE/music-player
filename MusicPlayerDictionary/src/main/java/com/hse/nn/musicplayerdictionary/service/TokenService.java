package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.dto.response.TokenResponse;
import org.springframework.security.core.token.Token;

public interface TokenService {
    TokenResponse allocateToken(String extendedInformation);

     boolean verifyToken(String key);
}
