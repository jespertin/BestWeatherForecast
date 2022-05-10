package se.iths.bestweatherforecast.model;

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

    public void setNameRef(String nameRef) {
        this.nameRef = nameRef;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }


    @Override
    public String toString() {
        return "WeatherForecast{" +
                "nameRef='" + nameRef + '\'' +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                '}';
    }

}
