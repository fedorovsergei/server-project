package com.sf.ui.service;

import com.sf.ui.entity.WeatherHistory;
import com.sf.ui.repo.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Transactional
    public WeatherHistory getWeather() {
        LocalDateTime now = roundTime(LocalDateTime.now(ZoneId.of("Europe/Moscow")));
        log.info("start search weather to date {}", now);
        WeatherHistory weatherHistory = weatherRepository.findByTime(now);
        if (weatherHistory == null) {
            log.error("weather not found in db, call remote page");
            throw new NotFoundException("weather not found");
        }
        log.info("return find weather for {}", weatherHistory.getTime());
        return Objects.requireNonNull(weatherHistory);
    }

    private LocalDateTime roundTime(LocalDateTime now) {
        return now.truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(10 * (now.getMinute() / 10));
    }
}
