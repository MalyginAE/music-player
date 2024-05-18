package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.dto.response.TokenResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Token;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.TokenRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("tokenService")
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    private String key = "qwertyuiopasdfghjkljdfnjsafdjsflfhaasdjfhdskfkjafkjasfhlkfjzxcvbnm";//todo to env

    private final Map<String, String> accessTokens = new HashMap<>();
    private final TokenRepository tokenRepository;
    private final UserService userService;

    @Override
    public TokenResponse allocateToken(String extendedInformation, User user) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();

        var token = tokenRepository.findTokenByRefreshToken(extendedInformation)
                .map(tokenData -> {
                    var accessToken = generateAccessToken(name);
                    accessTokens.put(accessToken, tokenData.getRefreshToken());
                    return new TokenResponse(tokenData.getRefreshToken(), accessToken);
                })
                .orElseGet(() -> {
                    String refreshToken = generateRefreshToken(name);
                    String accessToken = generateAccessToken(name);
                    accessTokens.put(accessToken, refreshToken);
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

    @Override
    public boolean verifyToken(String token) {
//        JwtParser parser = Jwts.parser()
//                .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key)))
//                .build();
//        boolean signed = parser.isSigned(token);
//        Jwt<?, ?> jwt = parser.parse(token);
        return accessTokens.containsKey(token);
    }
}
