package com.hse.nn.musicplayerdictionary.model.entity;

import com.hse.nn.musicplayerdictionary.model.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "password", nullable = false)
    private String password;
}
