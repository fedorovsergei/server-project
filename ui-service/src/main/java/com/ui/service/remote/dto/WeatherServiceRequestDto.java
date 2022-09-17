package com.ui.service.remote.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WeatherServiceRequestDto {

    private String isin;
    private LocalDate date;
}
