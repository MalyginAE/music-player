package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.dto.response.TokenResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Token;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.TokenRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("tokenService")
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    @Value("${token}")
    private String key;
    private final CacheManager cacheManager;
    private final TokenRepository tokenRepository;

    @Override
    public TokenResponse allocateToken(String extendedInformation, User user) {
        Cache tokens = cacheManager.getCache("tokens");
        var token = tokenRepository.findTokenByRefreshToken(extendedInformation)
                .map(tokenData -> {
                    var accessToken = generateAccessToken(user.getUserName());
                    tokens.put(accessToken, tokenData.getRefreshToken());
                    return new TokenResponse(tokenData.getRefreshToken(), accessToken);
                })
                .orElseGet(() -> {
                    String refreshToken = generateRefreshToken(user.getUserName());
                    String accessToken = generateAccessToken(user.getUserName());
                    log.debug("generated tokens: {}, refresh token:{}", accessToken, refreshToken);
                    tokens.put(accessToken, refreshToken);
                    tokenRepository.save(new Token(refreshToken, user));
                    return new TokenResponse(refreshToken, accessToken);
                });
        return token;
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

    @SneakyThrows
    @Override
    public boolean verifyToken(String token) {
        Cache tokens = cacheManager.getCache("tokens");
        return tokens.retrieve(token).get() != null;
    }
}
