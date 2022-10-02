package com.sf.ui.service;

import com.sf.ui.entity.WeatherHistory;
import com.sf.ui.repo.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherHistory getWeather() {
        log.info("start search weather");
        WeatherHistory weatherHistory = weatherRepository.findByTime(LocalDateTime.now(ZoneId.of("Europe/Moscow")));
        if (weatherHistory == null) {
            log.error("weather not found in db, call remote page");
            throw new NotFoundException("weather not found");
        }
        log.info("return find weather for {}", weatherHistory.getTime());
        return Objects.requireNonNull(weatherHistory);
    }
}
