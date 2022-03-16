package com.example.betandwin.weather;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String getWeather(Model model, @RequestParam(value = "city", required = false) String city) {
        WeatherDto dto = weatherService.getWeather(city);
        if (!(dto == null)) {
            model.addAttribute("weather", dto);
            return "/weather";
        } else {
            return "/error/nocity";
        }
    }
}
