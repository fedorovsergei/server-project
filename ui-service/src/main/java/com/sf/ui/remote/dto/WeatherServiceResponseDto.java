package com.sf.ui.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class WeatherServiceResponseDto {

    private LocalDateTime time;
    private String valueNow;
    private String valueFeeling;
}
