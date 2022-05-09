package se.iths.bestweatherforecast.smhi;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class SMHIRestClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";
    private final String oneDayFromNow = LocalDateTime.now().plusDays(1).minusHours(2).toString().substring(0, 13);

    public WeatherForecast getForecast(){
        WeatherForecast forecastLiljeholmen = restTemplate.getForObject(URL,WeatherForecast.class);
        return forecastLiljeholmen;
    }

    public Double getTemp(){
        List<TimeSeries> timeSeries = restTemplate.getForObject(URL, WeatherForecast.class)
                .getTimeSeries();

        TimeSeries seriesPlus24FromNow = null;

        //Kanske man vill ha en metod som returnera korrekt timeseries, sen kan man anropa den
        // i andra metoder.
        for (TimeSeries series : timeSeries) {
            if (series.getValidTime().contains(oneDayFromNow))
                seriesPlus24FromNow = series;
        }

        for (Parameter parameter : seriesPlus24FromNow.getParameters()) {
            if (parameter.getUnit().equalsIgnoreCase("Cel"))
                return parameter.getValues().get(0);
        }
        //Should not be able to get here
        return null;
    }


}
