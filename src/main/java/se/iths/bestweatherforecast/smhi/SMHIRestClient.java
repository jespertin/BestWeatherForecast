package se.iths.bestweatherforecast.smhi;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;


@Component
public class SMHIRestClient {

    private final String NAME_REF = "SMHI";
    private final RestTemplate RESTTEMPLATE = new RestTemplate();
    private final String URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";
    private final String TWENTY_FOUR_HOURS_FROM_NOW = LocalDateTime.now().plusDays(1).minusHours(2).toString().substring(0, 13);
    private WeatherForecastSMHI forecast;

    @PostConstruct
    public void setForecast() {
        forecast = RESTTEMPLATE.getForObject(URL, WeatherForecastSMHI.class);
    }

    public String getNAME_REF() {
        return NAME_REF;
    }

    private TimeSeries getCorrectTimeSeries() {
        return forecast.getTimeSeries()
                .stream()
                .filter(timeSeries -> timeSeries.getValidTime().contains(TWENTY_FOUR_HOURS_FROM_NOW))
                .toList()
                .get(0);
    }

    public Double getTemp() {
        TimeSeries timeSeries = getCorrectTimeSeries();

        for (Parameter parameter : timeSeries.getParameters()) {
            if (parameter.getUnit().equalsIgnoreCase("Cel"))
                return parameter.getValues().get(0);
        }
        throw new NoSuchElementException("Could not find correct time-series");
    }

    public Double getPrecipitation() {
        for (Parameter parameter : getCorrectTimeSeries().getParameters()) {
            if (parameter.getName().equalsIgnoreCase("pmean")) {
                return parameter.getValues().get(0);
            }
        }
        throw new NoSuchElementException("Could not find correct time-series");
    }

}


