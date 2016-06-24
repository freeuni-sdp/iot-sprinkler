package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import ge.edu.freeuni.sdp.iot.service.sprinkler.service.HumidityService;

/**
 * Created by misho on 6/22/16.
 */
public class FakeHumidityService implements HumidityService {
    @Override
    public boolean isSoilMoist(String houseId) {
        return false;
    }
}
