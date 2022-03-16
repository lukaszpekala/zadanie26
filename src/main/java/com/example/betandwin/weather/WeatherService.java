package com.example.betandwin.weather;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WeatherService {
    private final WeatherDtoMapper weatherDtoMapper;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String APP_ID = "&appid=ef778ae52fe2cc8bc09cf590fb99ad4e";

    private static final String GEO_URL_BASE = "/geo/1.0/direct?q=";
    private static final String GEO_URL_MIDDLE = "&limit=1";

    private static final String WEATHER_URL_BASE = "/data/2.5/weather?";
    private static final String WEATHER_URL_MIDDLE = "&units=metric&lang=pl";

    public WeatherService(WeatherDtoMapper weatherDtoMapper) {
        this.weatherDtoMapper = weatherDtoMapper;
    }

    private Optional<GeoLocationDto> getCoordinatesByCity(String city) {
        String url = BASE_URL + GEO_URL_BASE + city + GEO_URL_MIDDLE + APP_ID;
        ResponseEntity<GeoLocationDto[]> response = restTemplate.getForEntity(url, GeoLocationDto[].class);
        GeoLocationDto[] dtoArray = response.getBody();
        if (dtoArray.length > 0) {
            return Optional.of(dtoArray[0]);
        } else {
            return Optional.empty();
        }
    }

    private Optional<LocationWeatherDto> getLocationWeather(String city) {
        Optional<GeoLocationDto> optionalDto = getCoordinatesByCity(city);
        if (optionalDto.isPresent()) {
            GeoLocationDto dto = optionalDto.get();
            String cityLocation = "lat=" + dto.getLat() + "&lon=" + dto.getLon();
            String url = BASE_URL + WEATHER_URL_BASE + cityLocation + WEATHER_URL_MIDDLE + APP_ID;
            return Optional.ofNullable(restTemplate.getForObject(url, LocationWeatherDto.class));
        } else {
            return Optional.empty();
        }
    }

    public Optional<WeatherDto> getWeather(String city) {
        Optional<LocationWeatherDto> optionalDto = getLocationWeather(city);
        return optionalDto.map(locationWeatherDto -> weatherDtoMapper.map(locationWeatherDto));
    }
}
