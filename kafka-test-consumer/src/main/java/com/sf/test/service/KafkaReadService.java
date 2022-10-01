package com.sf.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaReadService {

    @KafkaListener(topics = "test-producer")
    public void test(String message) {
        System.out.println(message);
    }
}
