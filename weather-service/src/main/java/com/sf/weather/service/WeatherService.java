package com.sf.weather.service;

import com.sf.weather.entity.WeatherHistory;
import com.sf.weather.repo.WeatherRepository;
import com.sf.weather.service.dto.ParamDto;
import com.sf.weather.util.ApplicationProperty;
import liquibase.repackaged.org.apache.commons.lang3.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final ApplicationProperty applicationProperty;

    @Transactional
    public WeatherHistory getTodayWeather() {
        LocalDateTime now = roundTime(LocalDateTime.now(ZoneId.of("Europe/Moscow")));
        log.info("start search weather to date {}", now);
        WeatherHistory weatherHistory = weatherRepository.findByTime(now);
        if (weatherHistory == null) {
            log.info("weather not found in db, call remote page");
            weatherHistory = callUrl(now);
        }
        log.info("return find weather for {}", weatherHistory.getTime());
        return Objects.requireNonNull(weatherHistory);
    }

    @Transactional
    public WeatherHistory callUrl(LocalDateTime now) {
        ParamDto paramDto = getWeather();
        if (StringUtils.isEmpty(paramDto.getNow())) {
            log.error("not found weather in remote page");
            throw new IllegalArgumentException();
        }
        return weatherRepository.save(parseToDto(paramDto.getNow(), paramDto.getFeeling(), roundTime(now)));
    }

    @SneakyThrows
    private ParamDto getWeather() {
        Document doc = Jsoup.connect(applicationProperty.getUrl())
                .userAgent(applicationProperty.getAgent()).get();
        Elements e = doc.getElementsByAttributeValue("class", applicationProperty.getWeatherNow());
        String now = null;
        String feeling = null;
        if (!e.isEmpty()) now = e.get(0).text();
        if (e.size() > 1) feeling = e.get(1).text();

        return ParamDto
                .builder()
                .now(now)
                .feeling(feeling)
                .build();
    }

    private LocalDateTime roundTime(LocalDateTime now) {
        return now.truncatedTo(ChronoUnit.HOURS)
                .plusMinutes(10 * (now.getMinute() / 10));
    }

    private WeatherHistory parseToDto(String weatherNow, String weatherFeeling, LocalDateTime now) {
        return WeatherHistory
                .builder()
                .valueNow(weatherNow)
                .valueFeeling(weatherFeeling)
                .time(now)
                .build();
    }
}
