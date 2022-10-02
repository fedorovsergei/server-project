package com.sf.ui.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class WeatherHistory {

    @Id
    private LocalDateTime time;
    private String valueNow;
    private String valueFeeling;
}
