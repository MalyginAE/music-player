package com.hse.nn.musicplayerdictionary.repository.postgres;

import com.hse.nn.musicplayerdictionary.model.entity.Like;
import com.hse.nn.musicplayerdictionary.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    List<Like> findAllByUser(User user);
}
