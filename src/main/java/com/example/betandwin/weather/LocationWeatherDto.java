package com.example.betandwin.weather;



import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LocationWeatherDto {
    private Map<String, Double> coord;
    private List<WeatherConditionDto> weather;
    private String base;
    private Map<String, Double> main;
    private Integer visibility;
    private Map<String, Double> wind;
    private Map<String, Integer> clouds;
    private Long dt;
    private String name;

}
