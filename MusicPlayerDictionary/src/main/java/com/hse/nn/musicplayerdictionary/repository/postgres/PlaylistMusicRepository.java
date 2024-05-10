package com.hse.nn.musicplayerdictionary.repository.postgres;

import com.hse.nn.musicplayerdictionary.model.entity.PlayListMusic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistMusicRepository extends JpaRepository<PlayListMusic,Long> {

}
