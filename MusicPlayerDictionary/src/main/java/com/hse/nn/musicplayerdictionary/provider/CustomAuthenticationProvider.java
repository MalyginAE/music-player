package com.hse.nn.musicplayerdictionary.provider;

import com.hse.nn.musicplayerdictionary.model.Role;
import com.hse.nn.musicplayerdictionary.service.TokenService;
import io.jsonwebtoken.impl.security.PasswordSpec;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private String key = "qwertyuiopasdfghjkljdfnjsafdjsflfhaasdjfhdskfkjafkjasfhlkfjzxcvbnm";//todo to env
    private final TokenService tokenService;

    private final NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(key))).build(); //.build();

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken bearer = (BearerTokenAuthenticationToken) authentication;
        Jwt jwt = getJwt(bearer);
        if (!tokenService.verifyToken(bearer.getToken())) {
            return null;
        }
        String email = jwt.getClaimAsString("email");
        AbstractAuthenticationToken token = new JwtAuthenticationToken(jwt, List.of(Role.USER), email);
        if (token.getDetails() == null) {
            token.setDetails(bearer.getDetails());
        }
        log.debug("Authenticated token successfully");
        return token;
    }

    private Jwt getJwt(BearerTokenAuthenticationToken bearer) {
        try {
            return this.jwtDecoder.decode(bearer.getToken());
        } catch (BadJwtException failed) {
            log.debug("Failed to authenticate since the JWT was invalid");
            throw new InvalidBearerTokenException(failed.getMessage(), failed);
        } catch (JwtException failed) {
            throw new AuthenticationServiceException(failed.getMessage(), failed);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(BearerTokenAuthenticationToken.class);
    }

//    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password) {
//        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
//        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
//        final UserDetails principal = new User(name, password, grantedAuths);
//        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
//    }
}