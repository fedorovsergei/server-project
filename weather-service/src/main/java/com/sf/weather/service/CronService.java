package com.sf.weather.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Service
@RequiredArgsConstructor
public class CronService {

    private final WeatherService weatherService;

//    @Scheduled(cron = "${app.cron.remote-call}", zone = "Europe/Moscow")
    public void saveResultRemoteCall() {
        log.info("Scheduled job for call remote page start");
        try {
            weatherService.callUrl(LocalDateTime.now(ZoneId.of("Europe/Moscow")));
            log.info("Scheduled job finished");
        } catch (Exception e) {
            log.info("Scheduled job error: ", e);
        }
    }
}
