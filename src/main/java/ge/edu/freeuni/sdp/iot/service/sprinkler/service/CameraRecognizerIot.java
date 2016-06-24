package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

/**
 * Created by misho on 6/23/16.
 */
public class CameraRecognizerIot implements CameraRecognizer {
    @Override
    public boolean isUnknownObjectPresent(String houseId) {
        return false;
    }
}
