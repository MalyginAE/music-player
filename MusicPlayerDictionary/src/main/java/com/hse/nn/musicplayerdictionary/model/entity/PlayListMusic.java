package com.hse.nn.musicplayerdictionary.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Table(name = "playlist_music")
@Entity
@AllArgsConstructor
@Data
public class PlayListMusic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "music_id")
    @OneToOne
    private Music music;

    @JoinColumn(name = "playlist_id")
    @ManyToOne
    private PlayList playList;
}
