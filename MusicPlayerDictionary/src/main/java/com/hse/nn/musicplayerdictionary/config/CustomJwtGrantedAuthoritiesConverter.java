package com.hse.nn.musicplayerdictionary.config;

import com.hse.nn.musicplayerdictionary.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private final UserService userService;

    @Transactional
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        String email = source.getClaim("email");
        UserDetails userDetails;
        try {

            userDetails = userService.loadUserByUsername(email);
        } catch (UsernameNotFoundException exception) {
            log.info("user not fount, start register");
            userDetails = userService.create(email);
        }
        return (Collection<GrantedAuthority>) userDetails.getAuthorities();
    }
}
