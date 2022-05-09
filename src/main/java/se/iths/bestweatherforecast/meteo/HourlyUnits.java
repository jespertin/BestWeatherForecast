
package se.iths.bestweatherforecast.meteo;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "precipitation",
    "temperature_2m",
    "time"
})
@Generated("jsonschema2pojo")
public class HourlyUnits {

    @JsonProperty("precipitation")
    private String precipitation;
    @JsonProperty("temperature_2m")
    private String temperature2m;
    @JsonProperty("time")
    private String time;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("precipitation")
    public String getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    @JsonProperty("temperature_2m")
    public String getTemperature2m() {
        return temperature2m;
    }

    @JsonProperty("temperature_2m")
    public void setTemperature2m(String temperature2m) {
        this.temperature2m = temperature2m;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
