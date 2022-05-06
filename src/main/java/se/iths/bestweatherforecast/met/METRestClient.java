package se.iths.bestweatherforecast.met;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class METRestClient {


    private final String URL = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";
    private final String oneDayFromNow = LocalDateTime.now().plusDays(1).minusHours(2).toString().substring(0, 13);

    private final RestTemplate restTemplate = new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.USER_AGENT, "whateverNameYouWant")
            .build();

    public Double getTemp() {
        WeatherForecastMET forecastMET = restTemplate.getForObject(URL, WeatherForecastMET.class);
        List<Timeseries> timeseries = forecastMET.getProperties()
                .getTimeseries();

        Timeseries series24HoursFromNow = null;

        for (Timeseries series : timeseries) {
            if (series.getTime().contains(oneDayFromNow))
                series24HoursFromNow = series;
        }

        return series24HoursFromNow
                .getData()
                .getInstant()
                .getDetails()
                .getAirTemperature();
    }

}
