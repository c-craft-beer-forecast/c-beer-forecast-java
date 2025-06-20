package com.beer.beer_forecast.weather.service;

import com.beer.beer_forecast.weather.model.WeatherData;
import com.beer.beer_forecast.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public List<WeatherData> getAllWeather() {
        return weatherRepository.findAll();
    }
}

