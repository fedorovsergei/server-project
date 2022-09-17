package com.ui.service.controller;

import com.ui.service.remote.WeatherFeignService;
import com.ui.service.remote.dto.WeatherServiceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherFeignService weatherFeignService;

    @GetMapping("/get")
    public String getWeather(Model model) {
        WeatherServiceResponseDto weather = weatherFeignService.getWeather();
        System.out.println(weather);
        model.addAttribute("weather", weather);
        return "weather";
    }
}
