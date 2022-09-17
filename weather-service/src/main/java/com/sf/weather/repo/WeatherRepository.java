package com.sf.weather.repo;

import com.sf.weather.entity.WeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherHistory, Long> {

    WeatherHistory findByTime(LocalDateTime now);
}