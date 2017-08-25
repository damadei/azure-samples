package br.com.amadei.weather.api.service;

import br.com.amadei.weather.api.WeatherConditions;

public interface WeatherService {
    public WeatherConditions getWeatherConditions(String city, String country, String units);
}
