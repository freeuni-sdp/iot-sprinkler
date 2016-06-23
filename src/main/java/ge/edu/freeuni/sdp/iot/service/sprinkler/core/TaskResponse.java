package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by misho on 6/22/16.
 */
@XmlRootElement
public class TaskResponse {
    @XmlElement
    private String houseId;

    @XmlElement
    private String status;

    @XmlElement
    private int secondsLeft;

    public TaskResponse(String houseId, String status, int secondsLeft) {
        this.houseId = houseId;
        this.status = status;
        this.secondsLeft = secondsLeft;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }
}
