package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.model.dto.response.TokenResponse;
import com.hse.nn.musicplayerdictionary.service.TokenService;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jwt.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.Token;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class TokenController {
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity auth() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        var token = tokenService.allocateToken(name);
        return ResponseEntity.ok(token);

    }
}
