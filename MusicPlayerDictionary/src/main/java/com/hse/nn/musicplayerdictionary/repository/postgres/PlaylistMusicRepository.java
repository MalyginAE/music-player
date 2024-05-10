package com.hse.nn.musicplayerdictionary.repository.postgres;

import com.hse.nn.musicplayerdictionary.model.entity.Music;
import com.hse.nn.musicplayerdictionary.model.entity.PlayList;
import com.hse.nn.musicplayerdictionary.model.entity.PlayListMusic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistMusicRepository extends JpaRepository<PlayListMusic,Long> {

    Optional<PlayListMusic> findByPlayListAndMusic(PlayList playlist, Music music);

}
