package com.sf.weather.controller;

import com.sf.weather.entity.WeatherHistory;
import com.sf.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherHistory getTodayWeather() {
        log.info("[API] start search weather");
        try {
            return weatherService.getTodayWeather();
        } catch (Exception e) {
            log.error("Scheduled job error: ", e);
            throw e;
        }
    }
}