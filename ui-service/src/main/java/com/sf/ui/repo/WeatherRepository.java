package com.sf.ui.repo;

import com.sf.ui.entity.WeatherHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherHistory, Long> {

    @Query(value = """
            select * from weather_history where time < :time order by time desc limit 1
            """, nativeQuery = true)
    WeatherHistory findByTime(@Param(value = "time") LocalDateTime time);
}