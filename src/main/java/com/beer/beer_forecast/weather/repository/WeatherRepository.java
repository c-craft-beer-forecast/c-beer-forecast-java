package com.beer.beer_forecast.weather.repository;

import com.beer.beer_forecast.weather.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherData, Integer> {
}
