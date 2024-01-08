package com.hse.nn.musicplayerdictionary;

import com.hse.nn.musicplayerdictionary.repository.MusicTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class MusicPlayerDictionaryApplication {
	@Autowired
	private MusicTicketRepository musicTicketRepository;
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(MusicPlayerDictionaryApplication.class, args);
		applicationContext.getBean("ticketMapper");
	}
}
