package com.example.betandwin.weather;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(Model model, @RequestParam(required = false) String city) {
        WeatherDto dto = weatherService.getWeather(city);
        model.addAttribute("weather", dto);
        return "/weather";
    }

    @PostMapping("/weather")
    public String weather(Model model, WeatherDto dto) {
        WeatherDto weather = weatherService.getWeather(dto.getName());
        boolean foundCity = true;
        if (!dto.getName().equalsIgnoreCase(weather.getName())) {
            foundCity = false;
        }
        model.addAttribute("weather", weather);
        model.addAttribute("foundCity", foundCity);
        return "/weather";
    }
}
