
package se.iths.bestweatherforecast.met;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "precipitation_amount"
})
@Generated("jsonschema2pojo")
public class Details__2 {

    @JsonProperty("precipitation_amount")
    private Double precipitationAmount;


    @JsonProperty("precipitation_amount")
    public Double getPrecipitationAmount() {
        return precipitationAmount;
    }

    @JsonProperty("precipitation_amount")
    public void setPrecipitationAmount(Double precipitationAmount) {
        this.precipitationAmount = precipitationAmount;
    }

}
