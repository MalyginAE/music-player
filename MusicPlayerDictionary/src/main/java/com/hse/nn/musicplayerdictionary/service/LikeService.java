package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import com.hse.nn.musicplayerdictionary.model.dto.request.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Like;
import com.hse.nn.musicplayerdictionary.model.entity.Music;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.LikeRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.MusicRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final MusicRepository musicRepository;
    private final UserRepository userRepository;
    private final TicketMapper ticketMapper;

    public void saveLike(String trackId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(name).orElseThrow();
        Music music = musicRepository
                .findById(Long.valueOf(trackId))
                .orElseThrow(() -> new IllegalArgumentException("track not found"));
        Like like = Like.builder()
                .user(user)
                .music(music)
                .build();
        likeRepository.save(like);
    }

    public void deleteLike(String trackId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(name).orElseThrow();
        Music music = musicRepository
                .findById(Long.valueOf(trackId))
                .orElseThrow(() -> new IllegalArgumentException("track not found"));

        likeRepository.deleteLikeByMusicAndUser(music, user);
    }

    public List<MusicTicketResponse> getMusicTickets() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(name).orElseThrow();
        List<Like> likes = likeRepository.findAllByUser(user);
        return likes.stream()
                .map(Like::getMusic)
                .map(ticketMapper::ticketToResponseTickets)
                .toList();
    }
}
