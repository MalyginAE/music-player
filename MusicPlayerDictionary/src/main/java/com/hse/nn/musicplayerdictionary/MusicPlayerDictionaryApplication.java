package com.hse.nn.musicplayerdictionary;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableElasticsearchRepositories
@SpringBootApplication
@EnableCaching
public class MusicPlayerDictionaryApplication {
    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine.newBuilder().
                expireAfterWrite(20, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheNames(List.of("tokens"));
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setAsyncCacheMode(true);

        return caffeineCacheManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerDictionaryApplication.class, args);
    }
}
