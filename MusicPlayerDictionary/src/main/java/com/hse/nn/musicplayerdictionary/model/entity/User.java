package com.hse.nn.musicplayerdictionary.model.entity;

import com.hse.nn.musicplayerdictionary.model.Provider;
import com.hse.nn.musicplayerdictionary.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToMany(mappedBy = "id")
    private List<Music> musicList;

}
