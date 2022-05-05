package se.iths.bestweatherforecast.smhi;

import org.springframework.web.client.RestTemplate;

public class SMHIRestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherForecast getForecast(){

        WeatherForecast forecastLiljeholmen = restTemplate.getForObject("https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json"
                , WeatherForecast.class);
        return forecastLiljeholmen;
    }
}
