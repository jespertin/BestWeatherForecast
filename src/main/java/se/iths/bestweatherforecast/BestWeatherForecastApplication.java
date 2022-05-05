package se.iths.bestweatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.iths.bestweatherforecast.smhi.SMHIRestClient;

@SpringBootApplication
public class BestWeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestWeatherForecastApplication.class, args);


        SMHIRestClient client = new SMHIRestClient();
        System.out.println(client.getForecast());
    }
}
