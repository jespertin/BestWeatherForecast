package se.iths.bestweatherforecast.smhi;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;


public class SMHIRestClient {

    private final String nameRef = "SMHI";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";
    private final String oneDayFromNow = LocalDateTime.now().plusDays(1).minusHours(2).toString().substring(0, 13);
    private final WeatherForecast forecast = restTemplate.getForObject(URL, WeatherForecast.class);

    public String getNameRef() {
        return nameRef;
    }

    public WeatherForecast getForecast() {
        WeatherForecast forecastLiljeholmen = restTemplate.getForObject(URL, WeatherForecast.class);
        return forecastLiljeholmen;
    }

    private TimeSeries getCorrectTimeSeries() {
        return forecast.getTimeSeries()
                .stream()
                .filter(timeSeries -> timeSeries.getValidTime().contains(oneDayFromNow))
                .toList()
                .get(0);
    }

    public Double getTemp() {
        TimeSeries timeSeries = getCorrectTimeSeries();

        for (Parameter parameter : timeSeries.getParameters()) {
            if (parameter.getUnit().equalsIgnoreCase("Cel"))
                return parameter.getValues().get(0);
        }
        // Kanske inte returna null?
        return null;
    }

    public Double getPrecipitation(){
        for (Parameter parameter : getCorrectTimeSeries().getParameters()) {
            if (parameter.getName().equalsIgnoreCase("pmean")){
                return parameter.getValues().get(0);
            }
        }

        return null;
    }
}


