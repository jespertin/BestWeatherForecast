package se.iths.bestweatherforecast.met;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;


public class METRestClient {

    private final String nameRef = "Meteorologisk Institutt";
    private final RestTemplate restTemplate = new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.USER_AGENT, "whateverNameYouWant")
            .build();
    private final String URL = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";
    private final String oneDayFromNow = LocalDateTime.now().plusDays(1).minusHours(2).toString().substring(0, 13);
    private WeatherForecastMET forecastMET = restTemplate.getForObject(URL, WeatherForecastMET.class);

    public String getNameRef() {
        return nameRef;
    }

    private Timeseries getCorrectTimeSeries(){
        for (Timeseries timeseries : forecastMET.getProperties().getTimeseries()) {
            if (timeseries.getTime().contains(oneDayFromNow)){
                return timeseries;
            }
        }
        return null;
    }
    public Double getTemp() {
        return getCorrectTimeSeries()
                .getData()
                .getInstant()
                .getDetails()
                .getAirTemperature();
    }

    public Double getPrecipitation(){
        return getCorrectTimeSeries()
                .getData()
                .getNext1Hours()
                .getDetails()
                .getPrecipitationAmount();
    }

}
