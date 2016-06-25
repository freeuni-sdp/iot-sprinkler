package ge.edu.freeuni.sdp.iot.service.sprinkler.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by misho on 6/22/16.
 */
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {

    @XmlElement
    private String status;

    @XmlElement
    @JsonProperty(value = "duration", required = false)
    private Integer secondsLeft;

    public TaskResponse(String status, Integer secondsLeft) {
        this.status = status;
        this.secondsLeft = secondsLeft;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(Integer secondsLeft) {
        this.secondsLeft = secondsLeft;
    }
}
