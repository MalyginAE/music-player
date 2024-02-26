package com.music.player.musicplayerlistener.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultKafkaListener {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ApplicationContext context;

    @KafkaListener()
    @EventListener(classes = ApplicationStartedEvent.class)
    public void sendMessage() throws ExecutionException, InterruptedException {
        ProducerRecord<String, String> record = new ProducerRecord<>("test", "hi");
        SendResult<String, String> send = kafkaTemplate.send(record).get();

        int partition = send.getRecordMetadata().partition();
        log.info("sending message");
        System.out.println(partition);
    }

}
