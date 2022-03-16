package com.example.betandwin.weather;

import lombok.Data;

@Data
public class WeatherDto {
    private String icon;
    private String description;
    private Double temp;
    private Double feelsLike;
    private Double minTemp;
    private Double maxTemp;
    private Integer pressure;
    private Integer humidity;
    private Integer visibility;
    private Double windSpeed;
    private Integer clouds;
}
