package com.example.betandwin.weather;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class WeatherController {
    private static final String DEFAULT_CITY = "Wrocław";

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(Model model, @RequestParam(value = "city", required = false,
            defaultValue = DEFAULT_CITY) String city) {
        Optional<WeatherDto> dto = weatherService.getWeather(city);
        dto.ifPresent(weatherDto -> model.addAttribute("weather", weatherDto));
        if (dto.isPresent()) {
            model.addAttribute("city", city);
            return "/weather";
        } else {
            return "/error/nocity";
        }
    }
}
