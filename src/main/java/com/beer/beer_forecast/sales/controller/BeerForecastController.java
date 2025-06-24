package com.beer.beer_forecast.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.client.RestTemplate;

@Controller
public class BeerForecastController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.forecast.key}")
    private String apiKey;

    @GetMapping("/forecast")
    @ResponseBody
    public String forecast() {
        String apiUrl = "https://beer-forecast-function.azurewebsites.net/api/get_order_recommendations?code=" + apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        String result = response.getBody();
        return result;
    }
}
