package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.Provider;
import com.hse.nn.musicplayerdictionary.model.Role;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;



    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }





    public User create(String email) {
        log.info("User will created with email :{}", email);
        var user = com.hse.nn.musicplayerdictionary.model.entity.User.builder()
                .userName(email)
                .role(Role.USER)
                .provider(Provider.GOOGLE)
                .build();
        var savedUser = userRepository.save(user);
        return savedUser;
    }
}
