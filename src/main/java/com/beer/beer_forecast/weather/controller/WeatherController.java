package com.beer.beer_forecast.weather.controller;

import com.beer.beer_forecast.weather.service.WeatherService;
import com.beer.beer_forecast.weather.model.WeatherData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/admin/weather")  // URL変更済み
    public String showWeatherList(Model model) {
        List<WeatherData> weatherList = weatherService.getAllWeather();
        model.addAttribute("weatherList", weatherList);
        return "admin";
    }

}
