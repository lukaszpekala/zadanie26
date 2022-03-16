package com.example.betandwin.weather;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class GeoLocationDto {
    private String name;
    private Map<String, String> local_names;
    private BigDecimal lat;
    private BigDecimal lon;
    private String country;
    private String state;
}
