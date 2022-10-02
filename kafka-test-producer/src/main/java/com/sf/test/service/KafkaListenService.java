package com.sf.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KafkaListenService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(cron = "0/2 0/1 * 1/1 * *", zone = "Europe/Moscow")
    public void test() {
        kafkaTemplate.send("test-producer", "hello222" + LocalDateTime.now());
    }
}
