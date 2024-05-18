package com.hse.nn.musicplayerdictionary.config;

import com.hse.nn.musicplayerdictionary.model.Role;
import com.hse.nn.musicplayerdictionary.model.entity.User;
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
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private final List<GrantedAuthority> DEFAULT_USER_ROLES = List.of(Role.USER);
    private final UserService userService;

    @Transactional
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Collection<GrantedAuthority> roles = DEFAULT_USER_ROLES;
        String email = source.getClaim("email");
        User userDetails;
        try {
            userDetails = userService.findUserByUsername(email);
            roles = Collections.singleton(userDetails.getRole());
        } catch (UsernameNotFoundException exception) {
            log.info("user not fount, start register");
        }
        return roles;
    }
}
