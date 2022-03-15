package com.example.betandwin.weather;

import org.springframework.stereotype.Service;

@Service
public class WeatherDtoMapper {
    private static final String ICON_LINK = "https://openweathermap.org/img/wn/";

    WeatherDto map(LocationWeatherDto lwd, String city) {
        WeatherDto dto = new WeatherDto();
        dto.setName(city);
        dto.setIcon(ICON_LINK + lwd.getWeather().get(0).getIcon() + "@2x.png");
        dto.setDescription(lwd.getWeather().get(0).getDescription());
        dto.setTemp(Math.round(lwd.getMain().get("temp") * 10.0) / 10.0);
        dto.setFeelsLike(Math.round(lwd.getMain().get("feels_like") * 10.0) / 10.0);
        dto.setMinTemp(Math.round(lwd.getMain().get("temp_min") * 10.0) / 10.0);
        dto.setMaxTemp(Math.round(lwd.getMain().get("temp_max") * 10.0) / 10.0);
        dto.setPressure(lwd.getMain().get("pressure").intValue());
        dto.setHumidity(lwd.getMain().get("humidity").intValue());
        dto.setVisibility(lwd.getVisibility());
        dto.setWindSpeed(Math.round(lwd.getWind().get("speed") * 10.0) / 10.0);
        dto.setClouds(lwd.getClouds().get("all"));
        return dto;
    }
}
