package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.repository.UserRepository;
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
        return userRepository.findUserByUserName(username).map(user ->
                        User.builder()
                                .username(user.getUserName())
                                .password(user.getPassword())
                                .roles(user.getRole().name()).build())
                .orElseThrow(() -> new UsernameNotFoundException("user Not found"));
    }
}
