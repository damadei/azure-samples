package br.com.amadei.weather.api.service;

import br.com.amadei.weather.api.WeatherConditions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.plugin2.message.Message;

import java.text.MessageFormat;

@Component
public class WeatherServiceImpl implements WeatherService {
    private  static final int CONN_TIMEOUT = 5000;
    private  static final int READ_TIMEOUT = 10000;
    private static final String DEFAULT_UNITS = "metric";
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q={0}&units={1}&appid={2}";

    @Value(value = "${WEATHER_API_KEY}")
    private String apiKey;

    private RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(CONN_TIMEOUT);
        clientHttpRequestFactory.setReadTimeout(READ_TIMEOUT);
        return clientHttpRequestFactory;
    }

    public WeatherConditions getWeatherConditions(String city, String country, String units) {
        String url = getUrl(city, country, units);

        return restTemplate.getForObject(url, WeatherConditions.class);
    }

    private String getUrl(String city, String country, String units) {
        String cityAndCountry = city + (country != null && country.length() > 0 ? ("," + country) : "");

        if(units == null || units.trim().length() == 0) {
            units = DEFAULT_UNITS;
        }

        return MessageFormat.format(URL, cityAndCountry, units, apiKey);
    }
}
