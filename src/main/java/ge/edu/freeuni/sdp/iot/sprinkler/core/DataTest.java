package ge.edu.freeuni.sdp.iot.sprinkler.core;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by misho on 6/9/16.
 */
@XmlRootElement
public class DataTest {
    @XmlElement
    private String text;

    @XmlElement
    private String id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
