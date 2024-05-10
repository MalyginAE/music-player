package com.hse.nn.musicplayerdictionary.service;

import com.hse.nn.musicplayerdictionary.exception.DataNotFoundException;
import com.hse.nn.musicplayerdictionary.mapper.PlayListMapper;
import com.hse.nn.musicplayerdictionary.mapper.TicketMapper;
import com.hse.nn.musicplayerdictionary.model.dto.response.MusicTicketResponse;
import com.hse.nn.musicplayerdictionary.model.dto.response.PlaylistResponse;
import com.hse.nn.musicplayerdictionary.model.entity.Music;
import com.hse.nn.musicplayerdictionary.model.entity.PlayList;
import com.hse.nn.musicplayerdictionary.model.entity.PlayListMusic;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import com.hse.nn.musicplayerdictionary.repository.postgres.MusicRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.PlayListRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.PlaylistMusicRepository;
import com.hse.nn.musicplayerdictionary.repository.postgres.UserRepository;
import jakarta.transaction.Transactional;
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
    private final TicketMapper ticketMapper;
    private final PlaylistMusicRepository playlistMusicRepository;
    private final MusicRepository musicRepository;


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

    public List<MusicTicketResponse> getMusicByPlaylistName(String playlistName) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(userName).orElseThrow();
        PlayList playList = playListRepository.findByPlaylistNameAndUser(playlistName, user)
                .orElseThrow(() -> new DataNotFoundException("playlist not found"));
        return playList.getPlayListMusics().stream()
                .map(PlayListMusic::getMusic)
                .map(ticketMapper::ticketToResponseTickets)
                .toList();
    }

    @Transactional
    public void addMusicInPlaylist(String playlistName, String musicId) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(userName).orElseThrow();
        PlayList playList = playListRepository.findByPlaylistNameAndUser(playlistName, user)
                .orElseThrow(() -> new DataNotFoundException("playlist not found"));
        Music music = musicRepository.findById(Long.valueOf(musicId)).orElseThrow(() -> new DataNotFoundException("music not found"));
        var playListMusic = PlayListMusic.builder()
                .playList(playList)
                .music(music)
                .build();
        PlayListMusic savedPlayListMusic = playlistMusicRepository.save(playListMusic);
        playList.addPlayListMusic(savedPlayListMusic);
        playListRepository.save(playList);
    }

    @Transactional
    public void deleteMusicInPlaylist(String playlistName, String musicId) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(userName).orElseThrow();
        PlayList playList = playListRepository.findByPlaylistNameAndUser(playlistName, user)
                .orElseThrow(() -> new DataNotFoundException("playlist not found"));
        Music music = musicRepository.findById(Long.valueOf(musicId)).orElseThrow(() -> new DataNotFoundException("music not found"));

        PlayListMusic savedPlayListMusic = playlistMusicRepository.findByPlayListAndMusic(playList, music)
                .orElseThrow(() -> new DataNotFoundException("playlist not found"));
        playlistMusicRepository.delete(savedPlayListMusic);
    }

    @Transactional
    public void deletePlaylist(String playlistName) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByUserName(userName).orElseThrow();
        PlayList playList = playListRepository.findByPlaylistNameAndUser(playlistName, user)
                .orElseThrow(() -> new DataNotFoundException("playlist not found"));
        playListRepository.delete(playList);
    }
}

