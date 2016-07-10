package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.CameraRecognizer;

/**
 * Created by misho on 6/22/16.
 */
public class FakeCameraRecognizer implements CameraRecognizer {
    @Override
    public boolean isUnknownObjectPresent(String houseId) {
        if(houseId.compareTo("4") == 0) {
            return true;
        }
        return false;
    }
}
