package com.hse.nn.musicplayerdictionary.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MusicControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    void getPopularMusic() {
        mockMvc.perform(get("/hse/api/v1/music-player-dictionary/music/popular"))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"));
    }

    @SneakyThrows
    @Test
    void getNewMusic() {
        mockMvc.perform(get("/hse/api/v1/music-player-dictionary/music/new"))
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json"));
    }

    @SneakyThrows
    @Test
    void getMusic() {
        mockMvc.perform(get("/hse/api/v1/music-player-dictionary/music/1.mp3"))
                .andExpect(status().is(200))
                .andExpect(content().contentType("audio/mp3"));
    }
}