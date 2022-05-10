package se.iths.bestweatherforecast.meteo;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.util.List;

public class METEORestClient {

    private final String nameRef = "Open-Meteo";
    private RestTemplate restTemplate = new RestTemplate();
    private final String URL = "https://api.open-meteo.com/v1/forecast?latitude=59.3110&longitude=18.0300&hourly=temperature_2m,precipitation";
    private final String oneDayFromNow = LocalDateTime.now().plusDays(1).minusHours(2).toString().substring(0, 13);
    private  WeatherForecastMeteo forecastMeteo = restTemplate.getForObject(URL, WeatherForecastMeteo.class);


    public String getNameRef() {
        return nameRef;
    }

    public Double getTemp() {
        return forecastMeteo
                .getHourly()
                .getTemperature2m()
                .get(getIndexOfCorrectTime());
    }

    public Double getPrecipitation(){
        return forecastMeteo
                .getHourly()
                .getPrecipitation()
                .get(getIndexOfCorrectTime());

    }


    private int getIndexOfCorrectTime() {
        List<String> time = forecastMeteo.getHourly()
                .getTime();

        for (int i = 0; i < time.size(); i++) {
            if (time.get(i).contains(oneDayFromNow)) {
                return i;
            }
        }

        throw new IllegalStateException("Should not be able to get here");
    }

    private void setForecastMeteo() {
        forecastMeteo = restTemplate.getForObject(URL, WeatherForecastMeteo.class);
    }

}
