package se.iths.bestweatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.iths.bestweatherforecast.met.METRestClient;
import se.iths.bestweatherforecast.meteo.METEORestClient;
import se.iths.bestweatherforecast.service.BestWeatherCalculator;
import se.iths.bestweatherforecast.smhi.Parameter;
import se.iths.bestweatherforecast.smhi.SMHIRestClient;
import se.iths.bestweatherforecast.smhi.TimeSeries;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class BestWeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestWeatherForecastApplication.class, args);

        SMHIRestClient client = new SMHIRestClient();
        METRestClient metRestClient = new METRestClient();
        METEORestClient meteoRestClient = new METEORestClient();
        BestWeatherCalculator weatherCalculator = new BestWeatherCalculator(client,metRestClient,meteoRestClient);


        System.out.println("METEO: " + meteoRestClient.getTemp());
        System.out.println("METEO PRECIP: " + meteoRestClient.getPrecipitation());

        System.out.println("MET: " + metRestClient.getTemp());
        System.out.println("MET Precip: " + metRestClient.getPrecipitation());

        System.out.println("SMHI Precip: " + client.getPrecipitation());
        System.out.println("SMHI: " + client.getTemp());


        System.out.println(weatherCalculator.getBestWeatherForecast().toString());


    }
}
