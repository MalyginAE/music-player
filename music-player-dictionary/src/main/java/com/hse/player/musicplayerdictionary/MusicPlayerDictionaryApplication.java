package com.hse.player.musicplayerdictionary;

import com.hse.player.musicplayerdictionary.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class MusicPlayerDictionaryApplication {
	@Autowired
	private  TicketRepository ticketRepository;
	public static void main(String[] args) {
		SpringApplication.run(MusicPlayerDictionaryApplication.class, args);
	}



}
