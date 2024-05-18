package com.hse.nn.musicplayerdictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
@EnableCaching
public class MusicPlayerDictionaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerDictionaryApplication.class, args);
    }
}
