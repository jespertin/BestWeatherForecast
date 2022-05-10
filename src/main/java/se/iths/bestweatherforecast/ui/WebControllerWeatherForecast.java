package se.iths.bestweatherforecast.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.iths.bestweatherforecast.service.BestWeatherCalculator;

@Controller
public class WebControllerWeatherForecast {

    BestWeatherCalculator weatherCalculator;

    public WebControllerWeatherForecast(BestWeatherCalculator weatherCalculator) {
        this.weatherCalculator = weatherCalculator;
    }

    @GetMapping("/show-best-weather")
    public String getBestWeatherPage(Model model){
        model.addAttribute("bestForecast",weatherCalculator.getBestWeatherForecast());
        return "best_weather_forecast";
    }
}
