package se.iths.bestweatherforecast.smhi;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Component
public class SMHIRestClient {

    private final String NAME_REF = "SMHI";
    private final RestTemplate RESTTEMPLATE = new RestTemplate();
    private final String URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";

    public String getNAME_REF() {
        return NAME_REF;
    }

    private WeatherForecastSMHI getForecastSMHI() {
        return RESTTEMPLATE.getForObject(URL, WeatherForecastSMHI.class);
    }

    private String getTimeTwentyFourHoursFromNow() {
        return LocalDateTime.now(ZoneId.of("Z")).plusDays(1).toString().substring(0, 13);
    }

    private TimeSeries getCorrectTimeSeries() {
        return getForecastSMHI()
                .getTimeSeries()
                .stream()
                .filter(timeSeries -> timeSeries.getValidTime().contains(getTimeTwentyFourHoursFromNow()))
                .toList()
                .get(0);
    }

    public Double getTemp() {
        TimeSeries timeSeries = getCorrectTimeSeries();

        for (Parameter parameter : timeSeries.getParameters()) {
            if (parameter.getUnit().equalsIgnoreCase("Cel"))
                return parameter.getValues().get(0);
        }
        throw new NoSuchElementException("Could not find parameter unit Cel");
    }

    public Double getPrecipitation() {
        for (Parameter parameter : getCorrectTimeSeries().getParameters()) {
            if (parameter.getName().equalsIgnoreCase("pmean")) {
                return parameter.getValues().get(0);
            }
        }
        throw new NoSuchElementException("Could not find Parameter name pmean");
    }

}


