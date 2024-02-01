package com.hse.nn.musicplayerdictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.Transactional;

@EnableElasticsearchRepositories
@SpringBootApplication
public class MusicPlayerDictionaryApplication {
    @Transactional
    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerDictionaryApplication.class, args);
    }
}
