package com.example.betandwin.weather;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;

@Service
public class WeatherService {
    private final WeatherDtoMapper weatherDtoMapper;

    private static final String DEFAULT_CITY = "Wrocław";
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String APP_ID = "&appid=ef778ae52fe2cc8bc09cf590fb99ad4e";

    private static final String GEO_URL_BASE = "/geo/1.0/direct?q=";
    private static final String GEO_URL_MIDDLE = "&limit=1";

    private static final String WEATHER_URL_BASE = "/data/2.5/weather?";
    private static final String WEATHER_URL_MIDDLE = "&units=metric&lang=pl";

    public WeatherService(WeatherDtoMapper weatherDtoMapper) {
        this.weatherDtoMapper = weatherDtoMapper;
    }

    private GeoLocationDto getCoordinatesByCity(String city, RestTemplate restTemplate) {
        try {
            String url = BASE_URL + GEO_URL_BASE + city + GEO_URL_MIDDLE + APP_ID;
            ResponseEntity<GeoLocationDto[]> response = restTemplate.getForEntity(url, GeoLocationDto[].class);
            GeoLocationDto[] dtoArray = response.getBody();
            if (dtoArray.length > 0) {
                return dtoArray[0];
            }
            return getCoordinatesByCity(DEFAULT_CITY, restTemplate);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    public LocationWeatherDto getLocationWeather(String city) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            GeoLocationDto dto = getCoordinatesByCity(city, restTemplate);
            String cityLocation = "lat=" + dto.getLat() + "&lon=" + dto.getLon();
            String url = BASE_URL + WEATHER_URL_BASE + cityLocation + WEATHER_URL_MIDDLE + APP_ID;

            return restTemplate.getForObject(url, LocationWeatherDto.class);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    public WeatherDto getWeather(String city) {
        if (city == null) {
            city = DEFAULT_CITY;
        }
        LocationWeatherDto dto = getLocationWeather(city);
        if (!dto.getName().equalsIgnoreCase(city)) {
            city = dto.getName();
        }
        return weatherDtoMapper.map(dto, city);
    }
}
