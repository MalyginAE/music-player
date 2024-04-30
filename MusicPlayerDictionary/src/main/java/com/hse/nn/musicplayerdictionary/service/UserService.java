package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.Provider;
import com.hse.nn.musicplayerdictionary.model.Role;
import com.hse.nn.musicplayerdictionary.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username).map(UserService::mapUserToUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    private static UserDetails mapUserToUserDetails(com.hse.nn.musicplayerdictionary.model.entity.User user) {
        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().name()).build();
    }

    public UserDetails create(String email) {
        var user = com.hse.nn.musicplayerdictionary.model.entity.User.builder()
                .userName(email)
                .role(Role.USER)
                .provider( Provider.GOOGLE)
                .build();
        var savedUser = userRepository.save(user);
        return mapUserToUserDetails(savedUser);
    }
}
