package com.hse.player.musicplayerdictionary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestMusicPlayerDictionaryApplication {

	@Bean
	@ServiceConnection
	ElasticsearchContainer elasticsearchContainer() {
		return new ElasticsearchContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.5.2"));
	}

	public static void main(String[] args) {
		SpringApplication.from(MusicPlayerDictionaryApplication::main).with(TestMusicPlayerDictionaryApplication.class).run(args);
	}
}
