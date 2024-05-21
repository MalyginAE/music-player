package com.hse.nn.musicplayerdictionary.controller;

import com.hse.nn.musicplayerdictionary.service.LikeService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;


import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LikeControllerTest {

    @Mock
    private LikeService likeService;

    private LikeController likeController;

    @BeforeEach
    void setup() {
        likeController = new LikeController(likeService);
    }


    @SneakyThrows
    @Test
    void like() {
        //when
        ResponseEntity responseEntity = likeController.like("1");
        //then
        Assertions.assertEquals("200 OK",responseEntity.getStatusCode().toString());
    }

    @Test
    void resetLike() {
        //when
        ResponseEntity responseEntity = likeController.resetLike("1");
        //then
        Assertions.assertEquals("200 OK",responseEntity.getStatusCode().toString());
    }

    @Test
    void checkLike() {
        //when
        ResponseEntity responseEntity = likeController.checkLike("1");
        //then
        Assertions.assertEquals("200 OK",responseEntity.getStatusCode().toString());
    }

    @Test
    void likes() {
        //given
        when(likeService.getMusicTickets()).thenReturn(List.of());
        //when
        ResponseEntity responseEntity = likeController.likes();
        //then
        Assertions.assertEquals("200 OK",responseEntity.getStatusCode().toString());
        Assertions.assertNotNull(responseEntity.getBody());
    }
}