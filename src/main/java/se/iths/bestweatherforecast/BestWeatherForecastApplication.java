package se.iths.bestweatherforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.iths.bestweatherforecast.smhi.Parameter;
import se.iths.bestweatherforecast.smhi.SMHIRestClient;
import se.iths.bestweatherforecast.smhi.TimeSeries;

import java.util.List;

@SpringBootApplication
public class BestWeatherForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestWeatherForecastApplication.class, args);


        SMHIRestClient client = new SMHIRestClient();
        List<TimeSeries> timeSeries = client.getForecast()
                .getTimeSeries();
        List<Parameter> parameters = timeSeries.get(24)
                .getParameters();
        Parameter temp = null;


        for (Parameter parameter : parameters) {
            String unit = parameter.getUnit();
            if (unit.equalsIgnoreCase("Cel"))
                temp = parameter;
        }

        for (Double value : temp.getValues()) {
            System.out.println(value);
        }



    }
}
