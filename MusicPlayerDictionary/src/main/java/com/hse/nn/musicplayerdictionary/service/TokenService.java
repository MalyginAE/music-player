package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.dto.response.TokenResponse;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import org.springframework.security.core.token.Token;

public interface TokenService {
    TokenResponse allocateToken(String extendedInformation, User user);

    boolean verifyToken(String token);
}
