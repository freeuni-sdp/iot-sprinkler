package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by misho on 6/24/16.
 */
@XmlRootElement
public class RequestBody {
    @JsonProperty(value = "house_id", required = true)
    public String houseId;

    @JsonProperty(value = "set_status", required = true)
    public String status;

    @JsonProperty(required = false, defaultValue = "0")
    public int duration;
}
