package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.PlayListMapper;
import com.hse.nn.musicplayerdictionary.model.dto.response.PlaylistResponse;
import com.hse.nn.musicplayerdictionary.model.entity.PlayList;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.PlayListRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayListService {
    private final PlayListRepository playListRepository;
    private final UserRepository userRepository;
    private final PlayListMapper playListMapper;


    public void createPlaylist(String name) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(userName).orElseThrow();
        PlayList playList = PlayList.builder()
                .user(user)
                .playlistName(name)
                .build();
        playListRepository.save(playList);
    }

    public List<PlaylistResponse> getPlaylists() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(userName).orElseThrow();
        return playListRepository.findByUser(user)
                .stream()
                .map(playListMapper::ticketToResponseTicket)
                .toList();

    }
}
