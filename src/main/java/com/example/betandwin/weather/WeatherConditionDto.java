package com.example.betandwin.weather;

import lombok.Data;

@Data
public class WeatherConditionDto {
    private Integer id;
    private String main;
    private String description;
    private String icon;
}
