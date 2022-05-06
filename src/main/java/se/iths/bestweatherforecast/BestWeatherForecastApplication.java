package se.iths.bestweatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.iths.bestweatherforecast.met.METRestClient;
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

        System.out.println(metRestClient.getTemp());

        System.out.println(client.getTemp());


    }
}
