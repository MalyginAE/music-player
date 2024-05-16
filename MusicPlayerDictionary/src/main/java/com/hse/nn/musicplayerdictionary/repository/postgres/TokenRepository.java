package com.hse.nn.musicplayerdictionary.repository.postgres;

import com.hse.nn.musicplayerdictionary.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findTokenByRefreshToken(String refreshToken);
}
