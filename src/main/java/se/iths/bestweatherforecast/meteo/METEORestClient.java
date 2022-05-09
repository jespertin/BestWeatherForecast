package se.iths.bestweatherforecast.meteo;

import org.springframework.web.client.RestTemplate;

public class METEORestClient {

    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://api.open-meteo.com/v1/forecast?latitude=59.3110&longitude=18.0300&hourly=temperature_2m,precipitation";

    public Double getTemp(){
        WeatherForecastMeteo forecastMeteo = restTemplate.getForObject(URL, WeatherForecastMeteo.class);
        return forecastMeteo
                .getHourly()
                .getTemperature2m()
                .get(24);
    }
}
