package com.hse.nn.musicplayerdictionary.repository.postgres;

import com.hse.nn.musicplayerdictionary.model.entity.PlayList;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
    Optional<PlayList> findByPlaylistName(String playlistName);
    List<PlayList> findByUser(User user);
}
