package com.hse.nn.musicplayerdictionary.model.entity;

import com.hse.nn.musicplayerdictionary.model.Role;
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
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne
    @ToString.Exclude
    private User user;
}
