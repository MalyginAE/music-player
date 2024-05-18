package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.dto.response.TokenResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Token;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.TokenRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("tokenService")
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Value("${token}")
    private String key;
    private final TokenRepository tokenRepository;

    @Override
    public TokenResponse allocateToken(String refToken, User user) {
        String accessToken = generateAccessToken(user.getUserName());
        String refreshToken = null;
        Token token = user.getToken();
        if (token == null) {
            refreshToken = generateRefreshToken(user.getUserName());
            tokenRepository.save(new Token(refreshToken, user));
        } else if (refToken.equals(token.getRefreshToken())){
            refreshToken = token.getRefreshToken();
        }else {
            refreshToken = generateRefreshToken(user.getUserName());
            tokenRepository.save(token.setRefreshToken(refreshToken));
        }
        return new TokenResponse(refreshToken, accessToken);
    }

    private String generateAccessToken(String name) {
        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 1200000)) //20min
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    private String generateRefreshToken(String name) {
        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 15552000000L)) //180 days
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
}
