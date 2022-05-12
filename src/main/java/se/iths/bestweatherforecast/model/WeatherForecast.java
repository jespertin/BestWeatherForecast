package se.iths.bestweatherforecast.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeatherForecast {

    private String nameRef;
    private Double temperature;
    private Double precipitation;
    private LocalDateTime dateTime;

    public WeatherForecast(String nameRef, Double temperature, Double precipitation) {
        this.nameRef = nameRef;
        this.temperature = temperature;
        this.precipitation = precipitation;
        dateTime = LocalDateTime.now().plusDays(1);
    }

    public String getNameRef() {
        return nameRef;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @JsonIgnore
    public String getFormattedTime() {
        LocalDate date = dateTime.toLocalDate();
        int hour = dateTime.getHour();
        return date.toString() + " " + hour + ":00";
    }
}
