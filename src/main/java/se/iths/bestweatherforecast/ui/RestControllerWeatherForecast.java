package se.iths.bestweatherforecast.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.bestweatherforecast.model.WeatherForecast;
import se.iths.bestweatherforecast.service.BestWeatherCalculator;

@RestController
@RequestMapping("/api")
public class RestControllerWeatherForecast {

    BestWeatherCalculator weatherCalculator;

    public RestControllerWeatherForecast(BestWeatherCalculator weatherCalculator) {
        this.weatherCalculator = weatherCalculator;
    }

    @GetMapping("/show-best-weather")
    public WeatherForecast getBestWeather(){
        return weatherCalculator.getBestWeatherForecast();
    }

}
