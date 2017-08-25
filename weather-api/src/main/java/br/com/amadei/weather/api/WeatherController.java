package br.com.amadei.weather.api;

import br.com.amadei.weather.api.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/weather")
    public WeatherConditions getWeatherConditions(@RequestParam("city") String city,
                                                  @RequestParam(value = "country", required = false) String country,
                                                  @RequestParam(value = "units", required = false, defaultValue = "metric") String units) {

        return weatherService.getWeatherConditions(city, country, units);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();

        ErrorResponse error = new ErrorResponse();
        error.setMessage(ex.getMessage());

        return new ResponseEntity<Object>(error
                , HttpStatus.CONFLICT);
    }
}
