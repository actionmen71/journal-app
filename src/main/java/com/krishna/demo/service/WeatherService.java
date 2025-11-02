package com.krishna.demo.service;

import com.krishna.demo.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String api_key = "604d04ee3b7f7e98405c75d6cc6493c8";
    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;   //RestTemplate is interface and its implementation in main application file.


    public WeatherResponse getWeather(String city){
        String final_API = API.replace("CITY", city).replace("API_KEY", api_key);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(final_API, HttpMethod.GET,null,WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}






/* This weather service is for demonstration purposes.
* 1. To fetch api from external website or service.
* 2. To process the data and utilize it. */
