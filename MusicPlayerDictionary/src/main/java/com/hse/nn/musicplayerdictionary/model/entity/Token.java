package com.hse.nn.musicplayerdictionary.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    @ToString.Exclude
    private User user;

    public Token(String refreshToken, User user) {
        this.refreshToken = refreshToken;
        this.user = user;
    }

    public Token setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
