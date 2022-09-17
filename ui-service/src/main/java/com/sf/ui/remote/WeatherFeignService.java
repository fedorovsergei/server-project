package com.sf.ui.remote;

import com.sf.ui.remote.dto.WeatherServiceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "weather-service", url = "#{applicationProperties.weatherServiceUrl}", path = "/")
public interface WeatherFeignService {

    @GetMapping(value = "/weather")
    WeatherServiceResponseDto getWeather();
}
