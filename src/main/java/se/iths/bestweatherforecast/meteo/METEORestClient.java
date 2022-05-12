package se.iths.bestweatherforecast.meteo;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class METEORestClient {

    private final String NAME_REF = "Open-Meteo";
    private final RestTemplate RESTTEMPLATE = new RestTemplate();
    private final String URL = "https://api.open-meteo.com/v1/forecast?latitude=59.3110&longitude=18.0300&hourly=temperature_2m,precipitation";


    private WeatherForecastMETEO getForecastMETEO() {
        return RESTTEMPLATE.getForObject(URL, WeatherForecastMETEO.class);
    }

    public String getNAME_REF() {
        return NAME_REF;
    }

    private String getTimeTwentyFourHoursFromNow() {
        return LocalDateTime.now(ZoneId.of("Z")).plusDays(1).toString().substring(0, 13);
    }

    public Double getTemp() {
        return getForecastMETEO()
                .getHourly()
                .getTemperature2m()
                .get(getIndexOfCorrectTime());
    }

    public Double getPrecipitation() {
        return getForecastMETEO()
                .getHourly()
                .getPrecipitation()
                .get(getIndexOfCorrectTime());
    }

    private int getIndexOfCorrectTime() {
        List<String> time = getForecastMETEO().getHourly()
                .getTime();
        for (int i = 0; i < time.size(); i++) {
            if (time.get(i).contains(getTimeTwentyFourHoursFromNow())) {
                return i;
            }
        }
        throw new NoSuchElementException("Could not find correct index");
    }

}
