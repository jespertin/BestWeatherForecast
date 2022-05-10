package se.iths.bestweatherforecast.met;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Component
public class METRestClient {

    private final String NAME_REF = "Meteorologisk Institutt";
    private final RestTemplate RESTTEMPLATE = new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.USER_AGENT, "whateverNameYouWant")
            .build();
    private final String URL = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";
    private final String TWENTY_FOUR_HOURS_FROM_NOW = LocalDateTime.now().plusDays(1).minusHours(2).toString().substring(0, 13);

    private WeatherForecastMET forecastMET;

    @PostConstruct
    private void setForecastMET() {
        forecastMET = RESTTEMPLATE.getForObject(URL, WeatherForecastMET.class);
    }

    public String getNAME_REF() {
        return NAME_REF;
    }

    private Timeseries getCorrectTimeSeries() {
        for (Timeseries timeseries : forecastMET.getProperties().getTimeseries()) {
            if (timeseries.getTime().contains(TWENTY_FOUR_HOURS_FROM_NOW)) {
                return timeseries;
            }
        }
        throw new NoSuchElementException("Could not find correct time-series");
    }

    public Double getTemp() {
        return getCorrectTimeSeries()
                .getData()
                .getInstant()
                .getDetails()
                .getAirTemperature();
    }

    public Double getPrecipitation() {
        return getCorrectTimeSeries()
                .getData()
                .getNext1Hours()
                .getDetails()
                .getPrecipitationAmount();
    }

}
