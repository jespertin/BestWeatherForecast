package se.iths.bestweatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import se.iths.bestweatherforecast.met.METRestClient;
import se.iths.bestweatherforecast.meteo.METEORestClient;
import se.iths.bestweatherforecast.service.BestWeatherCalculator;
import se.iths.bestweatherforecast.smhi.Parameter;
import se.iths.bestweatherforecast.smhi.SMHIRestClient;
import se.iths.bestweatherforecast.smhi.TimeSeries;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@SpringBootApplication
public class BestWeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestWeatherForecastApplication.class, args);

    }
}
