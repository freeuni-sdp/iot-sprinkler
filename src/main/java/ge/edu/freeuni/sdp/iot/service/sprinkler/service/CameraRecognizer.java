package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

/**
 * Created by misho on 6/22/16.
 */
public interface CameraRecognizer {

    /*
        returns true if any unknown object is present on the stage.
        returns false there's no unknown objects present.
     */
    boolean isUnknownObjectPresent(String houseId);

}
