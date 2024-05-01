package com.hse.nn.musicplayerdictionary.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Table(name = "likes")
@Entity
@Data
@AllArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne()
    @JoinColumn(name = "music_id")
    private Music music;


    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

}
