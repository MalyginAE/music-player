package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository);
    }

    @Test
    void findUserByUsername() {
        when(userRepository.findUserByUserName("Andr"))
                .thenReturn(Optional.of(User.builder().userName("Andr").build()));
        User andr = userService.findUserByUsername("Andr");
        Assertions.assertEquals("Andr", andr.getUserName());
    }

    @Test
    void tryToFindUserByUsername() {
        when(userRepository.findUserByUserName("Andr"))
                .thenReturn(Optional.of(User.builder().userName("Andr").build()));
        var andr = userService.tryToFindUserByUsername("Andr");
        Assertions.assertTrue(andr.isPresent());
    }

    @Test
    void create() {
        when(userRepository.save(any()))
                .thenReturn(User.builder().userName("Andr").build());
        var andr = userService.create("e@mail.ru");
        Assertions.assertEquals("Andr", andr.getUserName());
    }
}