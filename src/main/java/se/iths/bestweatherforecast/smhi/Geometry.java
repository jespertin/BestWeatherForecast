
package se.iths.bestweatherforecast.smhi;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "coordinates"
})
@Generated("jsonschema2pojo")
public class Geometry {

    @JsonProperty("type")
    private String type;

    @JsonProperty("coordinates")
    private List<List<Double>> coordinates = null;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("coordinates")
    public List<List<Double>> getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(List<List<Double>> coordinates) {
        this.coordinates = coordinates;
    }


    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder();

        for (List<Double> coordinateList : coordinates) {
            for (Double cordinate : coordinateList) {
                sb.append(cordinate + ", ");
            }
        }
        return sb.toString();
    }
}
