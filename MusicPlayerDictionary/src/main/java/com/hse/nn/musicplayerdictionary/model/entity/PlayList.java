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

    @OneToMany()
    @Builder.Default
    @JoinColumn(name = "music_id")
    private List<Music> music = new ArrayList<>();

    @Column
    private String playlistName;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
