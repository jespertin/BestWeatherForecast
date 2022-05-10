package se.iths.bestweatherforecast.service;

import org.springframework.stereotype.Service;
import se.iths.bestweatherforecast.met.METRestClient;
import se.iths.bestweatherforecast.meteo.METEORestClient;
import se.iths.bestweatherforecast.model.WeatherForecast;
import se.iths.bestweatherforecast.smhi.SMHIRestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class BestWeatherCalculator {

    private SMHIRestClient smhiClient;
    private METRestClient metClient;
    private METEORestClient meteoClient;
    private List<WeatherForecast> forecasts = new ArrayList<>();

    public BestWeatherCalculator(SMHIRestClient smhiClient, METRestClient metClient, METEORestClient meteoClient) {
        this.smhiClient = smhiClient;
        this.metClient = metClient;
        this.meteoClient = meteoClient;
        populateForecasts();
    }

    private void populateForecasts(){
        forecasts.add(new WeatherForecast(smhiClient.getNAME_REF(), smhiClient.getTemp(), smhiClient.getPrecipitation()));
        forecasts.add(new WeatherForecast(metClient.getNAME_REF(), metClient.getTemp(), metClient.getPrecipitation()));
        forecasts.add(new WeatherForecast(meteoClient.getNAME_REF(), meteoClient.getTemp(), meteoClient.getPrecipitation()));
    }

    public WeatherForecast getBestWeatherForecast(){
        WeatherForecast bestWeatherForecast = forecasts.get(0);
        for (WeatherForecast forecast : forecasts) {

            if (bestWeatherForecast.getTemperature()<forecast.getTemperature())
                bestWeatherForecast = forecast;

            else if (bestWeatherForecast.getTemperature().equals(forecast.getTemperature())){

                if (bestWeatherForecast.getPrecipitation()>forecast.getPrecipitation())
                    bestWeatherForecast = forecast;
            }
        }
        return bestWeatherForecast;
    }

}
