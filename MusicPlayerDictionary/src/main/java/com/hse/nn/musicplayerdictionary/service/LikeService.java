package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.model.entity.Like;
import com.hse.nn.musicplayerdictionary.model.entity.Music;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.LikeRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.MusicRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final MusicRepository musicRepository;
    private final UserRepository userRepository;

    public void saveLike(String trackId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Music music = musicRepository
                .findById(Long.valueOf(trackId))
                .orElseThrow(() -> new IllegalArgumentException("track not found"));
        User user = userRepository.findUserByUserName(name).orElseThrow();
        Like like = Like.builder()
                .user(user)
                .music(music)
                .build();
        Like saved = likeRepository.save(like);
    }
}
