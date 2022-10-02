package com.sf.test.service;

import com.sf.test.util.ApplicationProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final ApplicationProperty applicationProperty;

    @Scheduled(cron = "0/2 0/1 * 1/1 * *", zone = "Europe/Moscow")
    public void test() {
        System.out.println(applicationProperty.getSandbox());
        System.out.println(applicationProperty.getReadOnly());
    }
}
