package com.hse.nn.musicplayerdictionary.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@Table(name = "playlists")
@Entity
@Data
@AllArgsConstructor
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "playList")
    @Builder.Default
    private List<PlayListMusic> playListMusics = new ArrayList<>();

    @Column
    private String playlistName;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public void addPlayListMusic(PlayListMusic playListMusic) {
    playListMusics.add(playListMusic);
playListMusic.setPlayList(this);
    }
}
