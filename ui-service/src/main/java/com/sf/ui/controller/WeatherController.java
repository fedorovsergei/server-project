package com.sf.ui.controller;

import com.sf.ui.remote.dto.WeatherServiceResponseDto;
import com.sf.ui.service.RemoteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final RemoteService remoteService;

    @Operation
    @GetMapping("/get")
    public String getWeather(Model model) {
        log.info("[API] start call method /weather/get");
        try {
            WeatherServiceResponseDto result = remoteService.getWeather();
            if (result != null) {
                model.addAttribute("weather", result);
                return "weather";
            }
        } catch (Exception e) {
            log.error("exception in ui service");
        }
        return "error";
    }
}
